package com.openclassrooms.mddapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.service.UserDetailsImpl;

/**
 * Service class for managing users.
 *
 * @author Tony
 * @version 1.0
 */
@Service // Spécialisation de @Component
public class UserService {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    /**
     * Retrieves the authenticated user details.
     *
     * @return User object representing the authenticated user.
     */
    public User getUser() {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        return userRepository.findByEmail(userDetails.getEmail()).orElse(null);
    }

    /**
     * Retrieves User details from an id.
     *
     * @param id a {@link java.lang.Integer} object
     * @return a {@link com.openclassrooms.mddapi.models.User} object
     */
    public User getUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    /**
     * Update password for the authenticated user.
     *
     * @param password string containing the new password.
     * @return User object representing the updated user.
     */
    public User updatePassword(String password) {
        /** Retrieves the authenticated user. */
        User user = getUser();

        user.setPassword(bcryptEncoder.encode(password));

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

    /**
     * Retrieves topics list from the authenticated user.
     *
     * @return Topic[] representing the list of User subscriptions.
     */
    public List<Topic> getUserSubscription() {
        /** Retrieves the authenticated user. */
        User user = getUser();
        List<Topic> subscriptions = user.getTopics();
        return subscriptions;

    }

    /**
     * Add Topic to subscription list of authenticated user.
     *
     * @param topic_id representing the id of the topic to subscribe.
     */
    public void subscribeTopic(Integer topic_id) {
        /** Retrieves the authenticated user. */
        User user = getUser();
        Topic topic = this.topicRepository.findById(topic_id).orElse(null);

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
        /** Retrieves the authenticated user. */
        User user = getUser();
        Topic topic = this.topicRepository.findById(topic_id).orElse(null);

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
