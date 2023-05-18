package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.models.Topic;
import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

    List<Topic> findByIdNotIn(Integer[] topic_id);

}
