package com.openclassrooms.mddapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Represents a login request.
 *
 * @author Tony
 * @version 1.0
 */
@Data
public class LoginRequest {
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Password is required")
	private String password;
}
