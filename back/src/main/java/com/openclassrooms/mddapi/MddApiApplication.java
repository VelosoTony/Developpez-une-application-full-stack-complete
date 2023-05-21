package com.openclassrooms.mddapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * The main class to start the MddApi application.
 *
 * @author Tony
 * @version 1.0
 */
@EnableJpaAuditing
@SpringBootApplication
public class MddApiApplication {

	/**
	 * The main method that starts the MddApi application.
	 *
	 * @param args The command-line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(MddApiApplication.class, args);
	}

}
