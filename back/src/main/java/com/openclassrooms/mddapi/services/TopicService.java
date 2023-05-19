package com.openclassrooms.mddapi.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service // Spécialisation de @Component
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserService userService;

    public List<Topic> getAllTopics() {
        return this.topicRepository.findAll();
    }

    public Topic getTopicById(Integer id) {
        return this.topicRepository.findById(id).get();
    }

    public List<Topic> getUnsubscribedTopics() {

        Integer[] topicsId = userService.getUserSubscription().stream().map(Topic::getId).toArray(Integer[]::new);
        if (topicsId.length == 0) {
            return this.topicRepository.findAll();
        }
        return this.topicRepository.findByIdNotIn(topicsId);

    }

}
