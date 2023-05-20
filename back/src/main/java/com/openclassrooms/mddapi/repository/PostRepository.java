package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.models.Post;

/**
 * Repository interface for managing Post entities.
 *
 * @author Tony
 * @version $Id: $Id
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
    /**
     * {@inheritDoc}
     *
     * Retrieves all posts sorted by date in descending order.
     */
    List<Post> findAll(Sort sortByDateDesc);
}
