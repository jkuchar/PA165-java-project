package cz.fi.muni.pa165.model.dao;

import cz.fi.muni.pa165.model.PersonName;
import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.model.config.PersistenceApplicationContext;
import cz.fi.muni.pa165.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Integration test
 * This file is part of PA165 school project.
 * @author jkuchar
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class UserDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
	private UserDao userDao;

    @Test
    public void testFindAll() throws Exception {
        // This is integration test and all tests are using the same database.
        // This means that findAll will return random number of User.
        // We can count only with data we create here.

        // AAA = http://wiki.c2.com/?ArrangeActAssert

        // arrange
        User u;
        userDao.create(u = buildUser());

        // act
        List<User> allUsers = userDao.findAll();

        // assert
        assertTrue(allUsers.contains(u)); // added user is there also
    }

    private User buildUser() {
        return User.create(PersonName.of("Jan Kuchar"), Role.ADMIN, "410077@mail.muni.cz"); // todo: rename to of()? or build?
    }

    @Test
    public void testFindById() throws Exception {

        User original = buildUser();
        assertNull(userDao.findById(original.getId()));
        userDao.create(original);

        { // local scope
            User retrieved = userDao.findById(original.getId());
            assertEquals(retrieved.getName(), PersonName.of("Jan Kuchar"));

            assertEquals(retrieved, original); // .equals()
            assertSame(retrieved, original); // compares instances; should be the same thanks to EntityManager identity map
        }

        userDao.delete(original);

        assertNull(userDao.findById(original.getId()));
    }

    @Test
    public void testFindByName() throws Exception {
        User original = buildUser();
        assertNull(userDao.findByName(original.getName()));

        userDao.create(original);

        { // local scope
            User retrieved = userDao.findByName(original.getName());
            assertEquals(retrieved.getName(), PersonName.of("Jan Kuchar"));

            assertEquals(retrieved, original); // .equals()
            assertSame(retrieved, original); // compares instances; should be the same thanks to EntityManager identity map
        }
    }

}