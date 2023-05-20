package com.openclassrooms.mddapi.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;

/**
 * Service class for managing posts and comments.
 */
@Service // Spécialisation de @Component

public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    /**
     * Retrieves all posts sorted by created date in descending order.
     *
     * @return the list of posts
     */
    public List<Post> getAllPosts() {
        return this.postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
    }

    /**
     * Retrieves a post by its ID.
     *
     * @param id the ID of the post
     * @return the post with the specified ID
     */
    public Post getPostById(Integer id) {
        return this.postRepository.findById(id).get();
    }

    /**
     * Creates a new post.
     *
     * @param post the post to create
     * @return the created post
     */
    public Post createPost(Post post) {
        return this.postRepository.save(post);

    }

    /**
     * Retrieves all comments for a post.
     *
     * @param id the ID of the post
     * @return the list of comments for the post with the specified ID
     */
    public List<Comment> getCommentsPostById(Integer id) {
        System.out.println("LIST COMMENT " + id);
        return this.commentRepository.findByPostId(id);
    }

    /**
     * Adds a comment to a post.
     *
     * @param id      the ID of the post
     * @param content the content of the comment
     * @return the created comment
     */
    public Comment addPostComment(Integer id, String content) {
        Comment newComment = Comment.builder()
                .content(content)
                .post(getPostById(id))
                .build();

        return this.commentRepository.save(newComment);
    }

}