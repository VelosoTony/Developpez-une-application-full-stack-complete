package com.openclassrooms.mddapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.response.UserResponse;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.service.UserDetailsImpl;

@Service // Spécialisation de @Component
public class UserService {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();

    }

    /**
     * * Retrieves User details from an email.
     * 
     * @param email representing the email of user to retrieve.
     * @return UserResponse reprensenting the User retrieved.
     */
    public UserResponse getUserByEmail(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        User usr = user.get();
        System.out.println(usr);
        UserResponse userResp = new UserResponse();
        userResp.setId(usr.getId());
        userResp.setUsername(usr.getUsername());
        userResp.setEmail(usr.getEmail());
        userResp.setCreated_date(usr.getCreatedDate());
        userResp.setUpdated_date(usr.getUpdatedDate());
        System.out.println(usr.getTopics());
        userResp.setTopics(usr.getTopics());
        System.out.println(userResp);
        return userResp;

    }

    /**
     * Retrieves the authenticated user details.
     * 
     * @return User object representing the authenticated user.
     */
    public User getUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Optional<User> user = userRepository.findByEmail(userDetails.getEmail());
        User usr = user.get();
        return usr;

    }

    /**
     * Update password for the authenticated user.
     * 
     * @param password string containing the new password.
     * @return User object representing the updated user.
     */
    public User updatePassword(String password) {

        User user = getUser();
        user.setPassword(bcryptEncoder.encode(password));

        System.out.println("Update Password for : " + user);
        return this.userRepository.save(user);
    }

    /**
     * Update the User details.
     * 
     * @param user object representing the new user details.
     * @return User object representing the updated user.
     */
    public User updateUser(User user) {

        return this.userRepository.save(user);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves topics list from the authenticated user.
     * 
     * @return List<Topic> representing the list of User subscriptions.
     */
    public List<Topic> getUserSubscription() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Optional<User> user = userRepository.findByEmail(userDetails.getEmail());
        User usr = user.get();
        List<Topic> subscriptions = usr.getTopics();
        return subscriptions;

    }

    /**
     * Add Topic to subscription list of authenticated user.
     * 
     * @param topic_id representing the id of the topic to subscribe.
     */
    public void subscribeTopic(Integer topic_id) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Topic topic = this.topicRepository.findById(topic_id).orElse(null);
        User user = this.userRepository.findById(userDetails.getId()).orElse(null);
        if (topic == null || user == null) {
            throw new NotFoundException();
        }

        boolean alreadySubscribe = user.getTopics().stream().anyMatch(o -> o.getId().equals(topic_id));
        if (alreadySubscribe) {
            throw new BadRequestException();
        }

        user.getTopics().add(topic);

        this.userRepository.save(user);

    }

    /**
     * Remove Topic to subscription list of authenticated user.
     * 
     * @param topic_id representing the id of the topic to unsubscribe.
     */
    public void unsubscribeTopic(Integer topic_id) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Topic topic = this.topicRepository.findById(topic_id).orElse(null);
        User user = this.userRepository.findById(userDetails.getId()).orElse(null);
        if (topic == null || user == null) {
            throw new NotFoundException();
        }

        boolean alreadySubscribe = user.getTopics().stream().anyMatch(o -> o.getId().equals(topic_id));
        if (!alreadySubscribe) {
            throw new BadRequestException();
        }

        user.setTopics(
                user.getTopics().stream().filter(topicList -> !topicList.getId().equals(topic_id))
                        .collect(Collectors.toList()));

        this.userRepository.save(user);

    }

}
