package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.models.Topic;
import java.util.List;

/**
 * Repository interface for managing Topic entities.
 *
 * @author Tony
 * @version $Id: $Id
 */
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    /**
     * Retrieves topics with IDs not present in the given array.
     *
     * @return a list of topics with IDs not present in the given array
     * @param topic_id an array of {@link java.lang.Integer} objects
     */
    List<Topic> findByIdNotIn(Integer[] topic_id);

}
