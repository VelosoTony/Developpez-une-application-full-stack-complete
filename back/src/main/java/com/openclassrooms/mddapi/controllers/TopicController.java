package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.payload.response.TopicListResponse;
import com.openclassrooms.mddapi.security.service.UserDetailsImpl;
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
@RequestMapping("/api/topics")
@Tag(name = "Topic")
public class TopicController {

	@Autowired
	private TopicService topicService;

	@Autowired
	private UserService userService;

	@GetMapping("")
	@Operation(summary = "Get list topics", description = "Retrieve information of all topics")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicListResponse.class))),
			@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
	public List<Topic> getTopics() {

		return topicService.getAllTopics();

	}

	@GetMapping("/subscribed")
	@Operation(summary = "Get subscribed topics", description = "Retrieve information of all subscribed topics")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicListResponse.class))),
			@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
	public List<Topic> getTopicsSubscribed() {

		return userService.getUserSubscription();

	}

	@PostMapping("/{id}/subscribe")
	public ResponseEntity<?> subscribe(@PathVariable("id") Integer topic_id) {
		try {
			UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			this.userService.subscribe(topic_id);
			System.out.println("Subscribe User[" + user.getId() + "] to Topic[" + topic_id + "]");
			return ResponseEntity.ok().build();
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/{id}/unsubscribe")
	public ResponseEntity<?> unsubscribe(@PathVariable("id") Integer topic_id) {
		try {
			UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			this.userService.unsubscribe(topic_id);
			System.out.println("Unsubscribe User[" + user.getId() + "] from Topic[" + topic_id + "]");
			return ResponseEntity.ok().build();
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
