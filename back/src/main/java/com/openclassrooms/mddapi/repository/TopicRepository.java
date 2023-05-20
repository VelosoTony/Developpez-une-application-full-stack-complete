package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.models.Topic;
import java.util.List;

/**
 * Repository interface for managing Topic entities.
 */
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    /**
     * Retrieves topics with IDs not present in the given array.
     *
     * @param topicIds the array of topic IDs to exclude
     * @return a list of topics with IDs not present in the given array
     */
    List<Topic> findByIdNotIn(Integer[] topic_id);

}
