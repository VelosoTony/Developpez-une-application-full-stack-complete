package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.request.PasswordRequest;
import com.openclassrooms.mddapi.dto.request.UserRequest;
import com.openclassrooms.mddapi.dto.response.UserResponse;
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
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Tag(name = "User")
public class UserController {

	@Autowired
	private UserService userService;

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
	public ResponseEntity<User> getUser() {

		return ResponseEntity.ok(userService.getUser());

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
	public ResponseEntity<User> updateUser(@RequestBody UserRequest userRequest) {

		User user = userService.getUser();
		user.setEmail(userRequest.getEmail());
		user.setUsername(userRequest.getUsername());
		user = userService.updateUser(user);
		return ResponseEntity.ok(user);
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
	public ResponseEntity<User> updatePassword(@RequestBody PasswordRequest passwordRequest) {

		String password = passwordRequest.getPassword();

		return ResponseEntity.ok(userService.updatePassword(password));
	}

	/**
	 * Retrieves the account information of a user specified by their email.
	 *
	 * @param email The email of the user.
	 * @return The ResponseEntity containing the user information.
	 */
	@GetMapping("/user/{email}")
	@Operation(summary = "Get user by email", description = "Retrieve account information about user specified by his email")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
			@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
	public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {

		return ResponseEntity.ok(userService.getUserByEmail(email));

	}

	/**
	 * Retrieve account information about all users.
	 *
	 * @return The ResponseEntity containing the list of users information.
	 */
	@GetMapping("/users")
	@Operation(summary = "Get all user", description = "Retrieve account information about all users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
			@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
	public ResponseEntity<Iterable<User>> getUsers() {

		return ResponseEntity.ok(userService.getUsers());

	}

	@GetMapping("/user/subscription")
	@Operation(summary = "Get all user", description = "Retrieve account information about all users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
			@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
	public ResponseEntity<List<Topic>> getUserSubscription() {

		return ResponseEntity.ok(userService.getUserSubscription());

	}

}
