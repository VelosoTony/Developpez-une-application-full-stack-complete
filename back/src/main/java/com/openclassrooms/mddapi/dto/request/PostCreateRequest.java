package com.openclassrooms.mddapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Represents a request to create a new post.
 *
 * @author Tony
 * @version 1.0
 */
@Data
public class PostCreateRequest {
    /**
     * The ID of the post.
     */
    @NotBlank
    private Integer id;

    /**
     * The title of the post.
     */
    @NotBlank
    private String title;

    /**
     * The content of the post.
     */
    @NotBlank
    public String content;

}
