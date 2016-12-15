package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.UserDTO;
import cz.fi.muni.pa165.api.facade.UserFacade;
import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.model.PersonName;
import cz.fi.muni.pa165.model.entity.User;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.UserService;
import cz.fi.muni.pa165.service.config.BeanMappingConfiguration;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

/**
 * @author rtrembecky
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
public class UserFacadeImplTest extends AbstractTestNGSpringContextTests {

    private UserFacade facade;
    private UserService service;

    private UUID id;

    @Autowired
    private BeanMappingService beanMappingService;

    @BeforeMethod
    public void setUp() throws Exception {
        service = Mockito.mock(UserService.class);
        facade = new UserFacadeImpl(beanMappingService, service);

        id = UUID.randomUUID();
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<User> source = new LinkedList<>();
        source.add(Mockito.mock(User.class));

        when(service.findAll()).thenReturn(source);

        // Act
        final List<UserDTO> collection = facade.findAll();

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new UserDTO());
    }

    @Test
    public void testFindById() {
        // Arrange
        User user = Mockito.mock(User.class);
        when(service.findById(id)).thenReturn(user);

        // Act
        final UserDTO dto = facade.findById(id);

        // Assert
        Assert.assertEquals(dto, new UserDTO());
    }

    @Test
    public void testFindByName() {
        // Arrange
        User user = Mockito.mock(User.class);
        PersonName name = PersonName.of("Jozko", "Mrkvicka");
        user.setName(name);
        when(service.findByName(name)).thenReturn(user);

        // Act
        final UserDTO dto = facade.findByName(name.getFirst(), name.getLast());

        // Assert
        Assert.assertEquals(dto, new UserDTO());
    }

    @Test
    public void testRegister() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setFirstName("Devil");
        userDTO.setLastName("Lucifer");
        userDTO.setRole(Role.USER);
        userDTO.setEmail("666@hell.underground");
        userDTO.setCreated(new Date("01/02/2016"));

        // Act
        UUID uuid = facade.register(userDTO, "password123+");

        // Assert
        Assert.assertNotEquals(id, uuid);
        verify(service, times(1)).register(any(User.class), any(String.class));
    }

    @Test
    public void testDelete() {
        // Arrange
        User user = Mockito.mock(User.class);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        when(service.findById(id)).thenReturn(user);
        doNothing().when(service).delete(user);

        // Act
        facade.delete(userDTO);

        // Assert
        verify(service, times(1)).delete(user);
    }

    @Test
    public void testUpdate() {
        // Arrange
        User user = Mockito.mock(User.class);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setFirstName("Devil");
        userDTO.setLastName("Lucifer");
        userDTO.setRole(Role.USER);
        userDTO.setEmail("666@hell.underground");
        when(service.findById(id)).thenReturn(user);
        doNothing().when(service).update(user);

        // Act
        facade.update(userDTO);

        // Assert
        verify(service, times(1)).update(user);
    }

}