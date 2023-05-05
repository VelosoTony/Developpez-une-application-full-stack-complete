package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.payload.response.TopicListResponse;
import com.openclassrooms.mddapi.services.TopicService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "Topic")
public class TopicController {

	@Autowired
	private TopicService topicService;

	@GetMapping("/topics")
	@Operation(summary = "Get list topics", description = "Retrieve information of all topics")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicListResponse.class))),
			@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content) })
	public List<Topic> getTopics() {

		return topicService.getTopics();

	}

}
