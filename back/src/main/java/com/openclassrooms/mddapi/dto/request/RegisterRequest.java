package com.openclassrooms.mddapi.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Represents a request to register a new user.
 *
 * @author Tony
 * @version 1.0
 */
@Data
public class RegisterRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}
