package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.*;
import lombok.Data;

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