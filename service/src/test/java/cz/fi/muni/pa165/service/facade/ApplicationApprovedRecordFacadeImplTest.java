package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.ApplicationApprovedRecordDTO;
import cz.fi.muni.pa165.api.dto.CarDTO;
import cz.fi.muni.pa165.api.dto.RentApplicationDTO;
import cz.fi.muni.pa165.api.dto.UserDTO;
import cz.fi.muni.pa165.api.facade.ApplicationApprovedRecordFacade;
import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.model.PersonName;
import cz.fi.muni.pa165.model.entity.ApplicationApprovedRecord;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.User;
import cz.fi.muni.pa165.service.*;
import cz.fi.muni.pa165.service.config.BeanMappingConfiguration;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

/**
 * @author rtrembecky
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
public class ApplicationApprovedRecordFacadeImplTest extends AbstractTestNGSpringContextTests {

    private ApplicationApprovedRecordFacade facade;
    private ApplicationApprovedRecordService service;
    private CarService carService;
    private UserService userService;

    private CarDTO carDTO;
    private UserDTO userDTO;
    private UUID id;

    @Inject
    private BeanMappingService beanMappingService;

    @BeforeMethod
    public void setUp() throws Exception {
        service = Mockito.mock(ApplicationApprovedRecordService.class);
        carService = Mockito.mock(CarService.class);
        userService = Mockito.mock(UserService.class);
        facade = new ApplicationApprovedRecordFacadeImpl(beanMappingService, service, userService, carService,
                Mockito.mock(RentApplicationService.class));

        id = UUID.randomUUID();
    }

    @Test
    public void testCreate() {
        ApplicationApprovedRecordDTO dto = new ApplicationApprovedRecordDTO();

        //todo: add some sets which are missing from CarDTO at the moment
        carDTO = new CarDTO();
        carDTO.setSerialNumber("R2D2");
        carDTO.setRegPlateNumber("456");
        carDTO.setManufacturer("Manufacturer");
        carDTO.setType("H510Q");
        carDTO.setNumberOfSeats(5);

        userDTO = new UserDTO();
        userDTO.setId(UUID.randomUUID());
        userDTO.setRole(Role.USER);
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setEmail("john.doe@company.com");
        userDTO.setCreated(new Date("2016/2/5"));

        dto.setId(id);
        dto.setCar(carDTO);
        dto.setUser(userDTO);
        dto.setComment("");
        dto.setCreated(new Date("2016/5/12"));
        dto.setFrom(new Date("2016/5/28"));
        dto.setTo(new Date("2016/6/3"));
        dto.setRentApplication(Mockito.mock(RentApplicationDTO.class));

        UUID id = facade.create(dto);


    }

    @Test
    public void testFindAllRecords() {
        // Arrange
        List<ApplicationApprovedRecord> source = new LinkedList<>();
        source.add(Mockito.mock(ApplicationApprovedRecord.class));

        when(service.findAll()).thenReturn(source);

        // Act
        final List<ApplicationApprovedRecordDTO> collection = facade.findAllRecords();

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new ApplicationApprovedRecordDTO());
    }

    @Test
    public void testFindRecordById() {
        // Arrange
        ApplicationApprovedRecord entity = Mockito.mock(ApplicationApprovedRecord.class);
        when(service.findById(id)).thenReturn(entity);

        // Act
        final ApplicationApprovedRecordDTO dto = facade.findRecordById(id);

        // Assert
        Assert.assertEquals(dto, new ApplicationApprovedRecordDTO());
    }

    @Test
    public void testFindAllRecordsByCar() {
        // Arrange
        List<ApplicationApprovedRecord> source = new LinkedList<>();
        source.add(Mockito.mock(ApplicationApprovedRecord.class));
        Car car = new Car("R2D2", "456", "Manufacturer", "H510Q", 5, new Date("2016/2/5"));

        when(service.findByCar(car)).thenReturn(source);
        when(carService.findCarById(car.getId())).thenReturn(car);

        // Act
        final List<ApplicationApprovedRecordDTO> collection = facade.findAllRecordsByCar(car.getId());

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new ApplicationApprovedRecordDTO());
    }

    @Test
    public void testFindAllRecordsByUser() {
        // Arrange
        List<ApplicationApprovedRecord> source = new LinkedList<>();
        source.add(Mockito.mock(ApplicationApprovedRecord.class));
        User user = new User(PersonName.of("John Doe"), Role.MANAGER, "john.doe@company.com", new Date("2016/2/5"));
        when(service.findByUser(user)).thenReturn(source);
        when(userService.findById(user.getId())).thenReturn(user);

        // Act
        final List<ApplicationApprovedRecordDTO> collection = facade.findAllRecordsByUser(user.getId());

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new ApplicationApprovedRecordDTO());
    }

    @Test
    public void testFindAllRecordsCreatedBetween() {
        // Arrange
        List<ApplicationApprovedRecord> source = new LinkedList<>();
        source.add(Mockito.mock(ApplicationApprovedRecord.class));

        Date from = new Date("01/01/2016");
        Date to = new Date("01/02/2016");

        when(service.getRecordsCreatedBetween(from, to)).thenReturn(source);

        // Act
        final List<ApplicationApprovedRecordDTO> collection = facade.findAllRecordsCreatedBetween(from, to);

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new ApplicationApprovedRecordDTO());
    }

}