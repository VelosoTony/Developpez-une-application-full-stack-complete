package com.openclassrooms.mddapi.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Utility class for JWT token generation, parsing, and validation.
 *
 * @author Tony
 * @version 1.0
 */
@Component
public class JwtTokenUtility {

	@Value("${jwt.token.validity}")
	private long JWT_TOKEN_VALIDITY;

	@Value("${jwt.secret}")
	private String JWT_SECRET;

	/**
	 * Retrieves the username from the JWT token.
	 *
	 * @param token the JWT token
	 * @return the username extracted from the token
	 */
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	/**
	 * Retrieves the issued-at date from the JWT token.
	 *
	 * @param token the JWT token
	 * @return the issued-at date extracted from the token
	 */
	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	/**
	 * Retrieves the expiration date from the JWT token.
	 *
	 * @param token the JWT token
	 * @return the expiration date extracted from the token
	 */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	/**
	 * Retrieves a specific claim from the JWT token.
	 *
	 * @param token          the JWT token
	 * @param claimsResolver the function to extract the desired claim from the
	 *                       token's claims
	 * @param <T>            the type of the desired claim
	 * @return the extracted claim
	 */
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * Generates a new JWT token for the specified user details.
	 *
	 * @param userDetails the user details
	 * @return the generated JWT token
	 */
	public String generateJwtToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
				.compact();
	}

	/**
	 * Checks if a token can be refreshed, i.e., it is not expired.
	 *
	 * @param token the JWT token
	 * @return true if the token can be refreshed, false otherwise
	 */
	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token));
	}

	/**
	 * Validates a JWT token against the provided user details.
	 *
	 * @param token       the JWT token
	 * @param userDetails the user details to validate against
	 * @return true if the token is valid for the user, false otherwise
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
