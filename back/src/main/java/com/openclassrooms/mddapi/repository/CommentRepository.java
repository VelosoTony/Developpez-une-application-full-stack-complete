package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.models.Comment;

/**
 * Repository interface for managing Comment entities.
 *
 * @author Tony
 * @version 1.0
 */

 public interface CommentRepository extends JpaRepository<Comment, Integer> {
    /**
     * Retrieves a list of comments associated with a specific post.
     *
     * @param id the ID of the post
     * @return a list of comments associated with the post
     */
    List<Comment> findByPostId(Integer id);
}
