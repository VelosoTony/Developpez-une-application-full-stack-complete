package com.openclassrooms.mddapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Subscription;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.response.UserResponse;
import com.openclassrooms.mddapi.repository.UserRepository;

@Service // Spécialisation de @Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse getUserById(Integer id) {

        Optional<User> user = userRepository.findById(id);

        User usr = user.get();
        UserResponse userResp = new UserResponse();
        userResp.setId(usr.getUser_id());
        userResp.setUsername(usr.getUsername());
        userResp.setEmail(usr.getEmail());
        userResp.setCreated_date(usr.getCreated_date());
        userResp.setUpdated_date(usr.getUpdated_date());

        return userResp;

    }

    public List<Subscription> getSubscriptionForUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getSubscriptions();
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

}
