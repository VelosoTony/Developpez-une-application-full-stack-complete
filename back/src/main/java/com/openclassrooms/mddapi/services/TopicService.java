package com.openclassrooms.mddapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.payload.response.TopicListResponse;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service // Spécialisation de @Component
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getTopics() {

        List<Topic> topics = topicRepository.findAll();

        return topics;
    }

}
