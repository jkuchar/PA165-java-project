package cz.fi.muni.pa165.Model.Entity;

import cz.fi.muni.pa165.Model.PersonName;
import cz.fi.muni.pa165.Model.Role;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.*;

/**
 * This file is part of PA165 school project.
 * @author jkuchar
 */
public class UserTest {

    private static User constructUser() {
        Date created = new Date();
        User user = new User(PersonName.of("John Doe"), Role.MANAGER, "john.doe@company.com", created);

        assertEquals(user.getName(), PersonName.of("John Doe"));
        assertEquals(user.getRole(), Role.MANAGER);
        assertEquals(user.getEmail(), "john.doe@company.com");
        assertEquals(user.getCreated(), created);

        return user;
    }

    @Test
    public void testCreate() throws Exception {
        User u = User.create("John", "Doe", Role.MANAGER, "john.doe@company.com");

        assertEquals(u.getName(), PersonName.of("John Doe"));
        assertEquals(u.getRole(), Role.MANAGER);
        assertEquals(u.getEmail(), "john.doe@company.com");
    }

    @Test
    public void testChangeRole() throws Exception {
        User u = constructUser();

        u.setRole(Role.ADMIN);

        assertEquals(u.getRole(), Role.ADMIN);
    }

    @Test
    public void testChangeName() throws Exception {
        User u = constructUser();

        u.setName(PersonName.of("Foo Bar"));

        assertEquals(u.getName(), PersonName.of("Foo Bar"));
    }


    @DataProvider
    private Object[][] testConstructWithNulls_dataProvider() {
        return new Object[][] {
                {null                    ,  Role.MANAGER, "john.doe@company.com", new Date()},
                {PersonName.of("John Doe"), null,         "john.doe@company.com", new Date()},
                {PersonName.of("John Doe"), Role.MANAGER, null,                   new Date()},
                {PersonName.of("John Doe"), Role.MANAGER, "john.doe@company.com", null      },
        };
    }

    @Test(dataProvider = "testConstructWithNulls_dataProvider")
    public void testConstructWithNulls(PersonName name, Role role, String email, Date created) {
        assertThrows(
                java.lang.IllegalArgumentException.class,
                () -> new User(name, role, email, created)
        );
    }
}