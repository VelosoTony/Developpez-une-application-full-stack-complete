package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.dto.response.TopicListResponse;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.security.service.UserDetailsImpl;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controller class providing CRUD operations for topics.
 * It is a RESTful controller
 *
 * @author Tony
 * @version 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/topics")
@Tag(name = "Topic")
public class TopicController {

	@Autowired
	private TopicService topicService;

	@Autowired
	private UserService userService;

	/**
	 * Retrieves information of all topics.
	 *
	 * @return Topic[] The list of all topics.
	 */
	@GetMapping("")
	@Operation(summary = "Get list topics", description = "Retrieve information of all topics")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicListResponse.class))),
			@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
	public List<Topic> getTopics() {

		return topicService.getUnsubscribedTopics();

	}

	/**
	 * Retrieves information of topics subscribed by authenticated user.
	 *
	 * @return Topic[] The list of topics subscribed by user.
	 */
	@GetMapping("/subscribed")
	@Operation(summary = "Get subscribed topics", description = "Retrieve information of all subscribed topics")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicListResponse.class))),
			@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
	public List<Topic> getTopicsSubscribed() {

		return userService.getUserSubscription();

	}

	/**
	 * Subscribes the authenticated user to a specified topic.
	 *
	 * @param topic_id The ID of the topic to subscribe to.
	 * @return ResponseEntity The response entity.
	 */
	@PostMapping("/{id}/subscribe")
	@Operation(summary = "Subscribe to a topic", description = "Subscribes the authenticated user to a specified topic.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
	public ResponseEntity<?> subscribe(@PathVariable("id") Integer topic_id) {
		try {
			UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			this.userService.subscribeTopic(topic_id);
			System.out.println("Subscribe User[" + user.getId() + "] to Topic[" + topic_id + "]");
			return ResponseEntity.ok().build();
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 *
	 * Unsubscribes the authenticated user from a specified topic.
	 *
	 * @param topic_id The ID of the topic to unsubscribe from.
	 * @return ResponseEntity The response entity.
	 */
	@DeleteMapping("/{id}/unsubscribe")
	@Operation(summary = "Unsubscribe to a topic", description = "Unsubscribes the authenticated user to a specified topic.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
	public ResponseEntity<?> unsubscribe(@PathVariable("id") Integer topic_id) {
		try {
			UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			this.userService.unsubscribeTopic(topic_id);
			System.out.println("Unsubscribe User[" + user.getId() + "] from Topic[" + topic_id + "]");
			return ResponseEntity.ok().build();
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
