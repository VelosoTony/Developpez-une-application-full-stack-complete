package com.openclassrooms.mddapi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.dto.request.CommentCreateRequest;
import com.openclassrooms.mddapi.dto.request.PostCreateRequest;
import com.openclassrooms.mddapi.dto.response.PostResponse;
import com.openclassrooms.mddapi.dto.response.TopicResponse;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.PostService;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controller class providing CRUD operations for posts and comments.
 * It is a RESTful controller
 *
 * @author Tony
 * @version 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/posts")
@Tag(name = "Post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostMapper postMapper;

    /**
     * Retrieves information of posts related to topics subscribed by user.
     *
     * @return Post[] The list of posts by interest.
     */
    @GetMapping("")
    @Operation(summary = "Get posts for user", description = "Retrieves information of posts related to topics subscribed by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicResponse.class))),
            @ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
    public ResponseEntity<List<PostResponse>> getPostsForUser() {

        List<Post> posts = this.postService.getPostsForUser();

        return ResponseEntity.ok(this.postMapper.toDto(posts));
    }

    /**
     * Retrieves information of a specified post by id.
     *
     * @param id The ID of the post to retrieve.
     * @return Post The specified post.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get post by id", description = "Retrieve information of specified post by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicResponse.class))),
            @ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
    public ResponseEntity<PostResponse> getPostById(@PathVariable("id") Integer id) {

        Post post = this.postService.getPostById(id);

        return ResponseEntity.ok(this.postMapper.toDto(post));
    }

    /**
     * Retrieves comments of a specified post by ID.
     *
     * @param id The ID of the post.
     * @return Comment[] The list of comments for the specified post.
     */
    @GetMapping("/{id}/comments")
    @Operation(summary = "Get comments for post by id", description = "Retrieve list of comments for specified post by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))),
            @ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
    public ResponseEntity<List<Comment>> getCommentsPostById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(postService.getCommentsPostById(id));
    }

    /**
     * Adds a comment to a specified post.
     *
     * @param id             The ID of the post.
     * @param commentRequest The comment create request containing the comment
     *                       content.
     * @return Comment The created comment.
     */
    @PostMapping("/{id}/comment")
    @Operation(summary = "Add a comment to post", description = "Add a comment to a specified post by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))),
            @ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
    public ResponseEntity<Comment> addPostComment(@PathVariable("id") Integer id,
            @RequestBody CommentCreateRequest commentRequest) {

        return ResponseEntity.ok(this.postService.addPostComment(id, commentRequest.getContent()));
    }

    /**
     * Creates a new post.
     *
     * @param postRequest The post create request containing the post title and
     *                    content.
     * @return ResponseEntity The response entity.
     */
    @PostMapping("")
    @Operation(summary = "Create a new post", description = "Create a new post for the authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
    public ResponseEntity<?> createPost(@RequestBody PostCreateRequest postRequest) {
        try {
            User usr = userService.getUser();

            Topic topic = topicService.getTopicById(postRequest.getId());

            Post newPost = new Post();
            newPost.setUser(usr);
            newPost.setTopic(topic);
            newPost.setTitle(postRequest.getTitle());
            newPost.setContent(postRequest.getContent());

            this.postService.createPost(newPost);

            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
