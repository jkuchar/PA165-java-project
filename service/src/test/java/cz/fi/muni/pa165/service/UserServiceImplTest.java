package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.PersonName;
import cz.fi.muni.pa165.model.dao.UserDao;
import cz.fi.muni.pa165.model.entity.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * @author rtrembecky
 */
public class UserServiceImplTest {

    @Mock
    UserDao dao;

    @InjectMocks
    UserService service;

    private UUID id;

    @BeforeMethod
    public void setUp() throws Exception {
        id = UUID.randomUUID();
        dao = Mockito.mock(UserDao.class);
        service = new UserServiceImpl(dao);
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<User> collection = new LinkedList<>();
        when(dao.findAll()).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(service.findAll(), collection);
    }

    @Test
    public void testFindById() {
        // Arrange
        User user = Mockito.mock(User.class);
        when(dao.findById(id)).thenReturn(user);

        // Act + Assert
        Assert.assertSame(service.findById(id), user);
    }

    @Test
    public void testFindByName() {
        // Arrange
        User user = Mockito.mock(User.class);
        PersonName name = PersonName.of("John", "Doe");
        user.setName(name);
        when(dao.findByName(name)).thenReturn(user);

        // Act + Assert
        Assert.assertSame(service.findByName(name), user);
    }

    @Test
    public void testRegister() {
        // Arrange
        User user = Mockito.mock(User.class);

        // Act
        service.register(user);

        // Assert
        verify(dao, times(1)).create(user);
    }

    @Test
    public void testDelete() {
        // Arrange
        User user = Mockito.mock(User.class);

        // Act
        service.delete(user);

        // Assert
        verify(dao, times(1)).delete(user);
    }

    @Test
    public void testUpdate() {
        // Arrange
        User user = Mockito.mock(User.class);

        // Act
        service.update(user);

        // Assert
        verify(dao, times(1)).update(user);
    }

}