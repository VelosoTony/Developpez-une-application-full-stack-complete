package com.openclassrooms.mddapi.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.repository.PostRepository;

@Service // Spécialisation de @Component

public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return this.postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
    }

    public Post getPostById(Integer id) {
        return this.postRepository.findById(id).get();
    }

    public Post createPost(Post post) {
        return this.postRepository.save(post);

    }

}