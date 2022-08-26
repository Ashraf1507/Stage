package com.bezkoder.springjwt;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.bezkoder.springjwt.models.ERole.*;

@SpringBootApplication
public class SpringBootSecurityJwtApplication {
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;


	public static void main(String[] args) {
    SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner() {
		return (args) -> {
			roleRepository.save(new Role(ROLE_ADMIN));
			roleRepository.save(new Role(ROLE_USER));
			roleRepository.save(new Role(ROLE_MODERATOR));
			User user1 = new User("achraf slimani","achraf@gmail.com", encoder.encode("achraf123"));
			Set<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName(ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
			user1.setRoles(roles);
			userRepository.save(user1);
		};
	};


}
