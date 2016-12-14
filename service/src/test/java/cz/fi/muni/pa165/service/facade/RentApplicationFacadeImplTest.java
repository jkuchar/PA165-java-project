package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.CarDTO;
import cz.fi.muni.pa165.api.dto.RentApplicationDTO;
import cz.fi.muni.pa165.api.dto.UserDTO;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.RentApplication;
import cz.fi.muni.pa165.model.entity.User;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.CarService;
import cz.fi.muni.pa165.service.RentApplicationService;
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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * @author jkuchar
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
public class RentApplicationFacadeImplTest extends AbstractTestNGSpringContextTests {

    private RentApplicationFacadeImpl uut;
    private RentApplicationService rentApplicationService;
    private UserService userService;
    private CarService carService;
    private final UUID someUUID = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");

    @Autowired
    private BeanMappingService beanMappingService;

    @BeforeMethod
    private void prepare() {
        uut = new RentApplicationFacadeImpl(
            beanMappingService,
            rentApplicationService = Mockito.mock(RentApplicationService.class),
            userService = Mockito.mock(UserService.class),
            carService = Mockito.mock(CarService.class)
        );
    }

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        List<RentApplication> source = new LinkedList<>();
        source.add(Mockito.mock(RentApplication.class));

        when(rentApplicationService.findAll()).thenReturn(source);

        // Act
        final List<RentApplicationDTO> collection = uut.findAll();

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new RentApplicationDTO());
    }

    @Test
    public void testFindById() throws Exception {
        // Arrange
        RentApplication entity = Mockito.mock(RentApplication.class);
        when(rentApplicationService.findById(someUUID)).thenReturn(entity);

        // Act
        final RentApplicationDTO dto = uut.findById(someUUID);

        // Assert
        Assert.assertEquals(dto, new RentApplicationDTO());
    }

    @Test
    public void testFindByCar() throws Exception {
        // Arrange
        List<RentApplication> source = new LinkedList<>();
        source.add(Mockito.mock(RentApplication.class));

        when(rentApplicationService.findByCar(someUUID)).thenReturn(source);

        // Act
        final List<RentApplicationDTO> collection = uut.findByCar(someUUID);

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new RentApplicationDTO());
    }

    @Test
    public void testFindByUser() throws Exception {
        // Arrange
        List<RentApplication> source = new LinkedList<>();
        source.add(Mockito.mock(RentApplication.class));

        when(rentApplicationService.findByUser(someUUID)).thenReturn(source);

        // Act
        final List<RentApplicationDTO> collection = uut.findByUser(someUUID);

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new RentApplicationDTO());
    }

    @Test
    public void testGetRecordsCreatedBetween() throws Exception {
        // Arrange
        List<RentApplication> source = new LinkedList<>();
        source.add(Mockito.mock(RentApplication.class));

        Date from = new Date("01/01/2016");
        Date to = new Date("01/02/2016");


        when(rentApplicationService.getRecordsCreatedBetween(from, to)).thenReturn(source);

        // Act
        final List<RentApplicationDTO> collection = uut.getRecordsCreatedBetween(from, to);

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new RentApplicationDTO());
    }

    // todo: enable when other user and car facades will be available
    @Test
    public void testCreate() throws Exception {
        // Arrange
        // 1. prepare test data (DTOs):
        RentApplicationDTO rentApplicationDTO = new RentApplicationDTO();

        CarDTO carDTO = new CarDTO();
        carDTO.setId(someUUID);
        rentApplicationDTO.setCar(carDTO);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(someUUID);
        rentApplicationDTO.setUser(userDTO);

        rentApplicationDTO.setComment("ahoj");
        rentApplicationDTO.setFrom(new Date("2016/01/01"));
        rentApplicationDTO.setTo(new Date("2016/02/01"));

        // 2. configure user rentApplicationService and entity:
        User userEntity = Mockito.mock(User.class);
        when(userService.findById(someUUID)).thenReturn(userEntity);

        // 3. configure car rentApplicationService and entity
        Car car = Mockito.mock(Car.class);
        when(carService.findCarById(someUUID)).thenReturn(car);

        // Act
        UUID id = uut.create(rentApplicationDTO);

        // Assert
        Assert.assertNotEquals(id, someUUID);
        Mockito.verify(rentApplicationService, times(1))
            .create(any(RentApplication.class));
    }

}