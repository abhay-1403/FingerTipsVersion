package com.example.springbootb;

import com.example.springbootb.entity.Role;
import com.example.springbootb.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.CommandLineRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FingerTipsSpringBootTest {

	@Mock
	private RoleRepository roleRepository;

	@InjectMocks
	private FingerTipsSpringBoot fingerTipsSpringBoot;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCommandLineRunner() throws Exception {
		// Given
		when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(null);
		when(roleRepository.findByName("ROLE_GUEST")).thenReturn(null);

		CommandLineRunner runner = fingerTipsSpringBoot.dataInitializer(roleRepository);

		// When
		runner.run();

		// Then
		ArgumentCaptor<Role> roleCaptor = ArgumentCaptor.forClass(Role.class);
		verify(roleRepository, times(2)).save(roleCaptor.capture());
		assertTrue(roleCaptor.getAllValues().stream().anyMatch(role -> "ROLE_ADMIN".equals(role.getName())));
		assertTrue(roleCaptor.getAllValues().stream().anyMatch(role -> "ROLE_GUEST".equals(role.getName())));
	}

	@Test
	void testAddRoleIfNotExist_whenRoleDoesNotExist() {
		// Given
		String roleName = "ROLE_USER";
		when(roleRepository.findByName(roleName)).thenReturn(null);

		// When
		fingerTipsSpringBoot.addRoleIfNotExist(roleRepository, roleName);

		// Then
		ArgumentCaptor<Role> roleCaptor = ArgumentCaptor.forClass(Role.class);
		verify(roleRepository).save(roleCaptor.capture());
		assertEquals(roleName, roleCaptor.getValue().getName());
	}

	@Test
	void testAddRoleIfNotExist_whenRoleExists() {
		// Given
		String roleName = "ROLE_USER";
		Role existingRole = new Role();
		existingRole.setName(roleName);
		when(roleRepository.findByName(roleName)).thenReturn(existingRole);

		// When
		fingerTipsSpringBoot.addRoleIfNotExist(roleRepository, roleName);

		// Then
		verify(roleRepository, never()).save(any(Role.class));
	}
}
