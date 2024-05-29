package com.example.springbootb;

import com.example.springbootb.entity.Role;
import com.example.springbootb.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication()
public class FingerTipsSpringBoot {

	public static void main(String[] args) {
		SpringApplication.run(FingerTipsSpringBoot.class, args);
	}

	@Bean
	public CommandLineRunner dataInitializer(RoleRepository roleRepository) {
		return args -> {
			// Add initial roles only if they don't already exist
			addRoleIfNotExist(roleRepository, "ROLE_ADMIN");
			addRoleIfNotExist(roleRepository, "ROLE_GUEST");
		};
	}
	void addRoleIfNotExist(RoleRepository roleRepository, String roleName) {
		Optional<Role> existingRole = Optional.ofNullable(roleRepository.findByName(roleName));
		if (!existingRole.isPresent()) {
			Role role = new Role();
			role.setName(roleName);
			roleRepository.save(role);
		}
	}
}
