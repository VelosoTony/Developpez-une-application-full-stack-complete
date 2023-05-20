package com.openclassrooms.mddapi.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

/**
 * Represents a response containing a message string.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    @Schema(description = "Returned message", example = "error")
    private String message;

}
