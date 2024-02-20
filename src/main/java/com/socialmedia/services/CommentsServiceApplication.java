package com.socialmedia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.socialmedia.services.entity.User;
import com.socialmedia.services.repository.UserRepository;



@SpringBootApplication
public class CommentsServiceApplication {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CommentsServiceApplication.class, args).getBean(CommentsServiceApplication.class).setup();
	}

	public void setup() {
		long users = userRepository.count();
		if(users > 0) {
			return;
		}

		User user1 = new User();
		user1.setUsername("user1");
		user1.setPassword("password1");
		user1.setEmail("user1@example.com");
		userRepository.save(user1);

		User user2 = new User();
		user2.setUsername("user2");
		user2.setPassword("password2");
		user2.setEmail("user2@example.com");
		userRepository.save(user2);

		User user3 = new User();
		user3.setUsername("user3");
		user3.setPassword("password3");
		user3.setEmail("user3@example.com");
		userRepository.save(user3);

		User user4 = new User();
		user4.setUsername("user4");
		user4.setPassword("password4");
		user4.setEmail("user4@example.com");
		userRepository.save(user4);

		User user5 = new User();
		user5.setUsername("user5");
		user5.setPassword("password5");
		user5.setEmail("user5@example.com");
		userRepository.save(user5);
	}
}
