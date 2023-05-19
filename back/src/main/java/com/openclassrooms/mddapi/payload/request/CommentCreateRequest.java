package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentCreateRequest {

    @NotBlank
    @Size(min = 10, max = 255)
    private String content;

}
