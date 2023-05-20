package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.models.Post;

/**
 * Repository interface for managing Post entities.
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
    /**
     * Retrieves all posts sorted by date in descending order.
     *
     * @param sortByDateDesc the sort object specifying the sorting order
     * @return a list of posts sorted by date in descending order
     */
    List<Post> findAll(Sort sortByDateDesc);
}
