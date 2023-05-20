package com.openclassrooms.mddapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.models.User;

/**
 * Repository interface for managing User entities.
 *
 * @author Tony
 * @version $Id: $Id
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Retrieves a user by email.
     *
     * @param email the email of the user
     * @return an Optional containing the user with the given email, or empty if not
     *         found
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user exists with the given email.
     *
     * @param email the email to check
     * @return true if a user with the given email exists, false otherwise
     */
    Boolean existsByEmail(String email);

}
