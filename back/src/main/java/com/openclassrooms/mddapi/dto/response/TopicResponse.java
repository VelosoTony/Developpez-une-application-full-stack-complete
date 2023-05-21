package com.openclassrooms.mddapi.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

/**
 * Represents the dto TopicResponse.
 *
 * @author Tony
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponse {

    @Schema(description = "Topic identifier", example = "1")
    private Integer id;

    @Schema(description = "Name", example = "Java")
    private String name;

    @Schema(description = "Description", example = "Java a POO language.")
    private String description;

}
