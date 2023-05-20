package com.openclassrooms.mddapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Represents a request object for creating a comment.
 *
 * @author Tony
 * @version $Id: $Id
 */
@Data
public class CommentCreateRequest {

    @NotBlank(message = "Content is required")
    @Size(min = 10, max = 255)
    private String content;

}
