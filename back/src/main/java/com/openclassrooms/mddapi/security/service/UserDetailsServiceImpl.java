package com.openclassrooms.mddapi.security.service;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.request.LoginRequest;
import com.openclassrooms.mddapi.dto.request.RegisterRequest;
import com.openclassrooms.mddapi.dto.response.JwtResponse;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.jwt.JwtTokenUtility;

/**
 * Service class for user details management and authentication.
 *
 * @author Tony
 * @version 1.0
 */
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

	/**
	 * {@inheritDoc}
	 *
	 * Retrieves a user's details by their email address.
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

		return UserDetailsImpl
				.builder()
				.id(user.getId())
				.username(user.getEmail())
				.email(user.getEmail())
				.password(user.getPassword())
				.build();
	}

	/**
	 * Authenticates a user using their email and password.
	 *
	 * @param authenticationRequest the login request containing email and password
	 * @return the JWT response containing the generated token
	 * @throws java.lang.Exception if authentication fails
	 */
	public JwtResponse login(LoginRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtility.generateJwtToken(userDetails);

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

	/**
	 * Registers a new user with the provided details.
	 *
	 * @param user the register request containing user details
	 * @return the JWT response containing the generated token
	 * @throws java.lang.Exception if user registration fails
	 */
	public JwtResponse register(RegisterRequest user) throws Exception {

		User newUser = User.builder()
				.email(user.getEmail())
				.username(user.getUsername())
				.password(bcryptEncoder.encode(user.getPassword()))
				.build();
		User savedUser = userRepository.save(newUser);

		String token = jwtTokenUtility.generateJwtToken(loadUserByUsername(savedUser.getEmail()));

		return new JwtResponse(token);

	}

}
