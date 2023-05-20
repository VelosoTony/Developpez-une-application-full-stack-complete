package com.openclassrooms.mddapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class to start the MddApi application.
 *
 * @author Tony
 * @version $Id: $Id
 */
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
