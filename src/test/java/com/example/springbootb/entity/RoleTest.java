package com.example.springbootb.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoleTest {

    @Test
    public void testRole() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_ADMIN");

        assertEquals(1L, role.getId());
        assertEquals("ROLE_ADMIN", role.getName());
        assertNotNull(role.getUsers());
    }

    // Uncomment the following tests if equals and hashCode methods exist in the Role class

    /*
    @Test
    public void testEquals() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ROLE_ADMIN");

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("ROLE_ADMIN");

        assertEquals(role1, role2);
    }

    @Test
    public void testHashCode() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ROLE_ADMIN");

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("ROLE_ADMIN");

        assertEquals(role1.hashCode(), role2.hashCode());
    }
    */
}