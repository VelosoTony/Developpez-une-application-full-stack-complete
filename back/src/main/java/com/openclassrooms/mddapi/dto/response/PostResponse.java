package com.openclassrooms.mddapi.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

/**
 * Represents the dto PostResponse.
 *
 * @author Tony
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    @Schema(description = "Post identifier", example = "1")
    private Integer id;

    @Schema(description = "Topic identifier", example = "1")
    private Topic topic;

    @Schema(description = "User identifier", example = "1")
    private User user;

    @Schema(description = "Title", example = "New version of Java")
    private String title;

    @Schema(description = "Content", example = "Lot of new features for this new version of Java.")
    private String content;

    @Schema(description = "date this Post was created", example = "2023-03-18T00:23:42")
    private LocalDateTime createdDate;

}
