package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostCreateRequest {
    @NotBlank
    private Integer id;

    @NotBlank
    private String title;

    @NotBlank
    public String content;

}
