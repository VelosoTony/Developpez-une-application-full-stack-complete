
package com.openclassrooms.mddapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for representing a Not Found error.
 * This exception is thrown when a requested resource is not found.
 * It extends the RuntimeException class and is annotated with @ResponseStatus
 * to indicate
 * that the HTTP response status should be set to 404 (Not Found) when this
 * exception is thrown.
 *
 * @author Tony
 * @version 1.0
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    // No additional methods or fields are defined in this class.
}
