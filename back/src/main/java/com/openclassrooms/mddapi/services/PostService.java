package com.openclassrooms.mddapi.services;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;

/**
 * Service class for managing posts and comments.
 *
 * @author Tony
 * @version 1.0
 */
@Service // Spécialisation de @Component
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    /**
     * Retrieves all posts sorted by created date in descending order.
     *
     * @return the list of posts
     */
    public List<Post> getAllPosts() {
        return this.postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
    }

    /**
     * Retrieves all posts sorted by created date in descending order.
     *
     * @return the list of posts
     */
    public List<Post> getPostsForUser() {

        List<Topic> topics = userService.getUserSubscription();
        /** If users doesn't have subscriptions, then return all topics */
        if (topics.isEmpty()) {
            return Collections.emptyList();
        }
        /** else return only Not subscribed topics */
        return this.postRepository.findByTopicNotIn(topics);
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
                .user(this.userService.getUser())
                .post(getPostById(id))
                .build();
        System.out.println(newComment);
        return this.commentRepository.save(newComment);
    }

}
