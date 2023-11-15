package com.cookbook;

import com.cookbook.domain.entity.Role;
import com.cookbook.domain.entity.UserEntity;
import com.cookbook.repository.RoleRepository;
import com.cookbook.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CookbookAdventureApplication{

	public static void main(String[] args) {
		SpringApplication.run(CookbookAdventureApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isPresent())
				return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			UserEntity admin = new UserEntity(1, "admin1", passwordEncoder.encode("admin_password1"), roles);
			userRepository.save(admin);
		};
	}

}
