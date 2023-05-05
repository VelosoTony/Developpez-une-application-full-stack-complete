package com.openclassrooms.mddapi.security.service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.jwt.JwtTokenUtility;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtility jwtTokenUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));

		return UserDetailsImpl
				.builder()
				.id(user.getId())
				.username(user.getEmail())
				.lastName(user.getLastName())
				.firstName(user.getFirstName())
				.password(user.getPassword())
				.build();
	}

	public JwtResponse login(JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtility.generateToken(userDetails);

		return new JwtResponse(token);
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	public JwtResponse register(RegisterRequest user) throws Exception {

		User newUser = User.builder()
				.email(user.getEmail())
				.name(user.getName())
				.password(bcryptEncoder.encode(user.getPassword()))
				.build();
		User savedUser = userRepository.save(newUser);

		String token = jwtTokenUtility.generateToken(loadUserByUsername(savedUser.getEmail()));

		return new JwtResponse(token);

	}

	public UserResponse me() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		User user = userRepository.findByEmail(email);

		UserResponse userMeDTO = new UserResponse();
		userMeDTO.setId(user.getId());
		userMeDTO.setName(user.getName());
		userMeDTO.setEmail(user.getEmail());
		userMeDTO.setCreated_at(user.getCreated_at());
		userMeDTO.setUpdated_at(user.getUpdated_at());

		return userMeDTO;
	}
}