package com.openclassrooms.mddapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.request.LoginRequest;
import com.openclassrooms.mddapi.dto.request.RegisterRequest;
import com.openclassrooms.mddapi.dto.response.JwtResponse;
import com.openclassrooms.mddapi.dto.response.MessageResponse;
import com.openclassrooms.mddapi.security.service.UserDetailsServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import jakarta.validation.Valid;

/**
 * Controller class for handling JWT authentication-related endpoints.
 * This class handles authentication and registration requests.
 * It is annotated with @RestController to indicate that it is a RESTful
 * controller,
 * and @RequestMapping to specify the base path for the endpoints.
 * It is also annotated with @Tag to categorize it under "Authentication" in API
 * documentation.
 *
 * @author Tony
 * @version 1.0
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
@Tag(name = "Authentication")
public class JwtAuthenticationController {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	/**
	 * Generates an authentication token based on the provided login request.
	 *
	 * @param authenticationRequest The login request containing email and password.
	 * @return ResponseEntity The response entity containing the JWT
	 *         response.
	 * @throws java.lang.Exception If an error occurs during the authentication
	 *                             process.
	 */
	@PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
	@Operation(summary = "Authentication", description = "Authentication with email and password, return JWT")
	@SecurityRequirements(value = {}) // remove authentication security
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtResponse.class))),
			@ApiResponse(responseCode = "401", description = "error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class), examples = {
					@ExampleObject(value = "{\"message\":\"error\"}") })) })
	public ResponseEntity<JwtResponse> generateAuthenticationToken(
			@RequestBody LoginRequest authenticationRequest) throws Exception {

		return ResponseEntity.ok(userDetailsService.login(authenticationRequest));
	}

	/**
	 * Registers a new user based on the provided registration request.
	 *
	 * @param user The registration request containing email, username, and
	 *             password.
	 * @return ResponseEntity The response entity containing the JWT
	 *         response.
	 * @throws java.lang.Exception If an error occurs during the registration
	 *                             process.
	 */
	@PostMapping(value = "/register", produces = "application/json", consumes = "application/json")
	@Operation(summary = "Registration", description = "Register new user with email, username and password, return JWT")
	@SecurityRequirements(value = {}) // remove authentication security
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtResponse.class))),
			@ApiResponse(responseCode = "400", description = "bad request", content = @Content(mediaType = "application/json", examples = {
					@ExampleObject(value = "{\"timestamp\": \"2023-03-19T01:02:21.868+00:00\",\n\"status\": \"400\",\n\"error\": \"Bad Request\",\n\"path\": \"/api/auth/register\"}") })) })
	public ResponseEntity<JwtResponse> register(
			@Valid @RequestBody RegisterRequest user) throws Exception {

		return ResponseEntity.ok(userDetailsService.register(user));
	}
}
