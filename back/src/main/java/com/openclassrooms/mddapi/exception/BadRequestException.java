package com.openclassrooms.mddapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for representing a Bad Request error.
 * This exception is thrown when a client's request is invalid or cannot be
 * processed.
 * It extends the RuntimeException class and is annotated with @ResponseStatus
 * to indicate
 * that the HTTP response status should be set to 400 (Bad Request) when this
 * exception is thrown.
 *
 * @author Tony
 * @version $Id: $Id
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    // No additional methods or fields are defined in this class.
}
