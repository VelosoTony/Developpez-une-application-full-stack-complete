package com.openclassrooms.mddapi.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Represents a request to update user information.
 *
 * @author Tony
 * @version 1.0
 */
@Data
public class UserRequest {

	@NotBlank
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

}
