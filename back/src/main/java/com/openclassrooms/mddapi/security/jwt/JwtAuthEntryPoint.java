package com.openclassrooms.mddapi.security.jwt;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * JWT Authentication Entry Point.
 * Handles unauthorized requests and sends an unauthorized response.
 *
 * @author Tony
 * @version 1.0
 */
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    /**
     * {@inheritDoc}
     *
     * Handles unauthorized requests and sends an unauthorized response.
     */
    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException e)
            throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
