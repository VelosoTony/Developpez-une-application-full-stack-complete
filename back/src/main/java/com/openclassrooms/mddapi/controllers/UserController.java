package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.request.PasswordRequest;
import com.openclassrooms.mddapi.dto.request.UserRequest;
import com.openclassrooms.mddapi.dto.response.TopicResponse;
import com.openclassrooms.mddapi.dto.response.UserResponse;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controller class providing CRUD operations for user.
 * It is a RESTful controller
 *
 * @author Tony
 * @version 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Tag(name = "User")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private TopicMapper topicMapper;

	/**
	 * Retrieves the account information of the authenticated user.
	 *
	 * @return The ResponseEntity containing the user information.
	 */
	@GetMapping("/user")
	@Operation(summary = "Get user information", description = "Retrieve account information")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
			@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
	public ResponseEntity<UserResponse> getUser() {
		try {
			User user = userService.getUser();

			if (user == null) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok().body(this.userMapper.toDto(user));
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Updates the account information of the authenticated user.
	 *
	 * @param userRequest The UserRequest object containing the updated user
	 *                    information.
	 * @return The ResponseEntity containing the updated user information.
	 */
	@PutMapping(value = "/user", produces = "application/json", consumes = "application/json")
	@Operation(summary = "Update user information", description = "Update account information")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
			@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
	public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
		try {
			User user = userService.getUser();

			if (user == null) {
				return ResponseEntity.notFound().build();
			}
			user.setEmail(userRequest.getEmail());
			user.setUsername(userRequest.getUsername());

			User updatedUser = this.userService.updateUser(user);

			return ResponseEntity.ok().body(this.userMapper.toDto(updatedUser));
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Updates the password of the authenticated user.
	 *
	 * @param passwordRequest The PasswordRequest object containing the new
	 *                        password.
	 * @return The ResponseEntity containing the updated user information.
	 */
	@PutMapping(value = "/user/password", produces = "application/json", consumes = "application/json")
	@Operation(summary = "Update user password", description = "Update password")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
			@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
	public ResponseEntity<UserResponse> updatePassword(@RequestBody PasswordRequest passwordRequest) {
		try {
			User user = userService.getUser();

			if (user == null) {
				return ResponseEntity.notFound().build();
			}

			User updatedUser = userService.updatePassword(passwordRequest.getPassword());

			return ResponseEntity.ok().body(this.userMapper.toDto(updatedUser));
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}

	}

	/**
	 * "Retrieve topics subscribed by authenticated user
	 *
	 * @return The ResponseEntity containing the list of topic subscribed by use.
	 */
	@GetMapping("/user/subscription")
	@Operation(summary = "Get all topic subscribed by user", description = "Retrieve topics subscribed by authenticated user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicResponse.class))),
			@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
	public ResponseEntity<List<TopicResponse>> getUserSubscription() {

		List<Topic> topics = userService.getUserSubscription();

		return ResponseEntity.ok(this.topicMapper.toDto(topics));

	}

}
