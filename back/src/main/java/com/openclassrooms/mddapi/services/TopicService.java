package com.openclassrooms.mddapi.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

/**
 * Service class for managing topics.
 *
 * @author Tony
 * @version 1.0
 */
@Service // Spécialisation de @Component
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserService userService;

    /**
     * Retrieves all topics.
     *
     * @return the list of topics
     */
    public List<Topic> getAllTopics() {
        return this.topicRepository.findAll();
    }

    /**
     * Retrieves a topic by its ID.
     *
     * @param id the ID of the topic
     * @return the topic with the specified ID
     */
    public Topic getTopicById(Integer id) {
        return this.topicRepository.findById(id).get();
    }

    /**
     * Retrieves the topics that the user has not subscribed to.
     *
     * @return the list of unsubscribed topics
     */
    public List<Topic> getUnsubscribedTopics() {

        Integer[] topicsId = userService.getUserSubscription().stream().map(Topic::getId).toArray(Integer[]::new);
        /** If users doesn't have subscriptions, then return all topics */
        if (topicsId.length == 0) {
            return this.topicRepository.findAll();
        }
        /** else return only Not subscribed topics */
        return this.topicRepository.findByIdNotIn(topicsId);

    }

}
