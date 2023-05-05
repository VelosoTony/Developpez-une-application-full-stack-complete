package com.openclassrooms.mddapi.payload.response;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    @Schema(description = "User identifier", example = "1")
    private Long id;
    @Schema(description = "User name", example = "Robert")
    private String username;
    @Schema(description = "User email", example = "Robert@mail.com")
    private String email;
    @Schema(description = "date this user was created", example = "2023-03-18T00:23:42")
    private LocalDateTime created_date;
    @Schema(description = "date this user was updated", example = "2023-03-18T00:23:42")
    private LocalDateTime updated_date;

}
