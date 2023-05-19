package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.models.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAll(Sort sortByDateDesc);
}
