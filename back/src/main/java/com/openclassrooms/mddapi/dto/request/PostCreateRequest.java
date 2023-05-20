package com.openclassrooms.mddapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Represents a request to create a new post.
 */
@Data
public class PostCreateRequest {
    @NotBlank
    private Integer id;

    @NotBlank
    private String title;

    @NotBlank
    public String content;

}