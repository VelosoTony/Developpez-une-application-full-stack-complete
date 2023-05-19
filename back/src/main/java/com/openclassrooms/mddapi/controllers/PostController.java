package com.openclassrooms.mddapi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.PostCreateRequest;
import com.openclassrooms.mddapi.payload.response.TopicListResponse;
import com.openclassrooms.mddapi.security.service.UserDetailsImpl;
import com.openclassrooms.mddapi.services.PostService;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

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

    @GetMapping("")
    @Operation(summary = "Get list posts", description = "Retrieve information of all posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicListResponse.class))),
            @ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
    public List<Post> getPosts() {

        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get post by id", description = "Retrieve information of specified post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicListResponse.class))),
            @ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
    public Post getPostById(@PathVariable("id") Integer id) {

        return postService.getPostById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> createPost(@RequestBody PostCreateRequest postRequest) {
        try {
            UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            User usr = userService.getUserById(user.getId());

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