package com.openclassrooms.mddapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.response.JwtResponse;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
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

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class JwtAuthenticationController {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
	@Operation(summary = "Authentication", description = "Authentication with email and password, return JWT")
	@SecurityRequirements(value = {}) // remove authentication security
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = com.openclassrooms.mddapi.payload.response.JwtResponse.class))),
			@ApiResponse(responseCode = "401", description = "error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class), examples = {
					@ExampleObject(value = "{\"message\":\"error\"}") })) })
	public ResponseEntity<com.openclassrooms.mddapi.payload.response.JwtResponse> generateAuthenticationToken(
			@RequestBody LoginRequest authenticationRequest) throws Exception {

		return ResponseEntity.ok(userDetailsService.login(authenticationRequest));
	}

	@PostMapping(value = "/register", produces = "application/json", consumes = "application/json")
	@Operation(summary = "Registration", description = "Register new user with email, username and password, return JWT")
	@SecurityRequirements(value = {}) // remove authentication security
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtResponse.class))),
			@ApiResponse(responseCode = "400", description = "bad request", content = @Content(mediaType = "application/json", examples = {
					@ExampleObject(value = "{\"timestamp\": \"2023-03-19T01:02:21.868+00:00\",\n\"status\": \"400\",\n\"error\": \"Bad Request\",\n\"path\": \"/api/auth/register\"}") })) })
	public ResponseEntity<com.openclassrooms.mddapi.payload.response.JwtResponse> register(
			@Valid @RequestBody com.openclassrooms.mddapi.payload.request.RegisterRequest user) throws Exception {

		return ResponseEntity.ok(userDetailsService.register(user));
	}
}
