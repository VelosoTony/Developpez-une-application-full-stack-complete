package com.openclassrooms.mddapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    public UserResponse getUserByEmail(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        User usr = user.get();
        System.out.println(usr);
        UserResponse userResp = new UserResponse();
        userResp.setId(usr.getId());
        userResp.setUsername(usr.getUsername());
        userResp.setEmail(usr.getEmail());
        userResp.setCreated_date(usr.getCreated_date());
        userResp.setUpdated_date(usr.getUpdated_date());
        System.out.println(usr.getTopics());
        userResp.setTopics(usr.getTopics());
        System.out.println(userResp);
        return userResp;

    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public List<Topic> getUserSubscription() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Optional<User> user = userRepository.findByEmail(userDetails.getEmail());
        System.out.println(userDetails.getId());
        User usr = user.get();
        List<Topic> subscriptions = usr.getTopics();
        return subscriptions;

    }

    public void subscribe(Integer topic_id) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Topic topic = this.topicRepository.findById(topic_id).orElse(null);
        User user = this.userRepository.findById(userDetails.getId()).orElse(null);
        if (topic == null || user == null) {
            throw new NotFoundException();
        }

        boolean alreadySubscribe = user.getTopics().stream().anyMatch(o -> o.getTopic_id().equals(topic_id));
        if (alreadySubscribe) {
            throw new BadRequestException();
        }

        user.getTopics().add(topic);

        this.userRepository.save(user);

    }

    public void unsubscribe(Integer topic_id) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Topic topic = this.topicRepository.findById(topic_id).orElse(null);
        User user = this.userRepository.findById(userDetails.getId()).orElse(null);
        if (topic == null || user == null) {
            throw new NotFoundException();
        }

        boolean alreadySubscribe = user.getTopics().stream().anyMatch(o -> o.getTopic_id().equals(topic_id));
        if (!alreadySubscribe) {
            throw new BadRequestException();
        }

        user.setTopics(
                user.getTopics().stream().filter(topicList -> !topicList.getTopic_id().equals(topic_id))
                        .collect(Collectors.toList()));

        this.userRepository.save(user);

    }

}
