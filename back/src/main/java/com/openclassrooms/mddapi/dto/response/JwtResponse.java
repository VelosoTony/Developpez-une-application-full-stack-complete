package com.openclassrooms.mddapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a response containing a JWT token for authentication.
 *
 * @author Tony
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
  @Schema(description = "Jwt token authentication", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrYWthQGdtYWlsLmNvbSIsImV4cCI6MTY3OTI3OTYzNywiaWF0IjoxNjc5MTkzMjM3fQ.oz6u2RQ_OB5x1-R3wZM8cRl32Zme5SijrUnXCliU95nHniE2Q7qeLGSeXF8cjp_gVmxPgEhzS_Ig9x38RtQe4g")
  private String token;

}
