package com.openclassrooms.mddapi.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRequest {
    @Schema(description = "User password", example = "$2a$10$vYgMi0S/gNVWcWYTf5Bnce2kFCzrQShgdwyBwlskXJO8cIMsVIWr.")
    private String password;

}
