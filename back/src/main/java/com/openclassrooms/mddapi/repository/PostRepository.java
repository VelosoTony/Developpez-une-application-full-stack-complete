package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.Topic;

/**
 * Repository interface for managing Post entities.
 *
 * @author Tony
 * @version 1.0
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
    /**
     * {@inheritDoc}
     *
     * Retrieves all posts sorted by date in descending order.
     */
    List<Post> findAll(Sort sortByDateDesc);

    /**
     * Retrieves topics with IDs not present in the given array.
     *
     * @return a list of post with topic_IDs not present in the given array
     * @param topics list of subscribed topics for user
     */
    List<Post> findByTopicIn(List<Topic> topics);
}
