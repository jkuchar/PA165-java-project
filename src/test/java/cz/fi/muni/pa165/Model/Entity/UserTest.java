package cz.fi.muni.pa165.Model.Entity;

import cz.fi.muni.pa165.Model.Role;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.*;

/**
 * This file is part of PA165 school project.
 */
public class UserTest {

    private static User constructUser() {
        Date created = new Date();
        User user = new User("John", "Doe", Role.MANAGER, "john.doe@company.com", created);

        assertEquals(user.getFirstName(), "John");
        assertEquals(user.getLastName(), "Doe");
        assertEquals(user.getRole(), Role.MANAGER);
        assertEquals(user.getEmail(), "john.doe@company.com");
        assertEquals(user.getCreated(), created);

        return user;
    }

    @Test
    public void testCreate() throws Exception {
        User u = User.create("John", "Doe", Role.MANAGER, "john.doe@company.com");

        assertEquals(u.getFirstName(), "John");
        assertEquals(u.getLastName(), "Doe");
        assertEquals(u.getRole(), Role.MANAGER);
        assertEquals(u.getEmail(), "john.doe@company.com");
    }

    @Test
    public void testChangeRole() throws Exception {
        User u = constructUser();

        u.changeRole(Role.ADMIN);

        assertEquals(u.getRole(), Role.ADMIN);
    }

    @Test
    public void testChangeName() throws Exception {
        User u = constructUser();

        u.changeName("Foo", "Bar");

        assertEquals(u.getFirstName(), "Foo");
        assertEquals(u.getLastName(), "Bar");
    }

    @DataProvider
    private Object[][] testConstructWithNulls_dataProvider() {
        return new Object[][] {
                {null,  "Doe",  Role.MANAGER, "john.doe@company.com", new Date()},
                {"John", null,  Role.MANAGER, "john.doe@company.com", new Date()},
                {"John", "Doe", null,         "john.doe@company.com", new Date()},
                {"John", "Doe", Role.MANAGER, null,                   new Date()},
                {"John", "Doe", Role.MANAGER, "john.doe@company.com", null      },
        };
    }

    @Test(dataProvider = "testConstructWithNulls_dataProvider")
    public void testConstructWithNulls(String firstName, String lastName, Role role, String email, Date created) {
        assertThrows(
                java.lang.IllegalArgumentException.class,
                () -> new User(firstName, lastName, role, email, created)
        );
    }

}