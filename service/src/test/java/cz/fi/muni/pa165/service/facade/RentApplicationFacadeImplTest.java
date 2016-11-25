package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.RentApplicationDTO;
import cz.fi.muni.pa165.api.facade.RentApplicationFacade;
import cz.fi.muni.pa165.model.entity.RentApplication;
import cz.fi.muni.pa165.service.CarService;
import cz.fi.muni.pa165.service.RentApplicationService;
import cz.fi.muni.pa165.service.UserService;
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

import static org.mockito.Mockito.*;

/**
 * @author jkuchar
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
public class RentApplicationFacadeImplTest extends AbstractTestNGSpringContextTests {

    private RentApplicationFacade uut;
    private RentApplicationService service;
    private UserService userService;
    private CarService carService;
    private final UUID someUUID = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");

    @Inject
    private BeanMappingService beanMappingService;

    @BeforeMethod
    private void prepare() {
        uut = new RentApplicationFacadeImpl(
                beanMappingService,
                service = Mockito.mock(RentApplicationService.class),
                userService = Mockito.mock(UserService.class),
                carService = Mockito.mock(CarService.class)
        );
    }

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        List<RentApplication> source = new LinkedList<>();
        source.add(Mockito.mock(RentApplication.class));

        when(service.findAll()).thenReturn(source);

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
        when(service.findById(someUUID)).thenReturn(entity);

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

        when(service.findByCar(someUUID)).thenReturn(source);

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

        when(service.findByUser(someUUID)).thenReturn(source);

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


        when(service.getRecordsCreatedBetween(from, to)).thenReturn(source);

        // Act
        final List<RentApplicationDTO> collection = uut.getRecordsCreatedBetween(from, to);

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new RentApplicationDTO());
    }

    // todo: enable when other user and car facades will be available
    @Test(enabled = false)
    public void testCreate() throws Exception {
        // Arrange
        RentApplicationDTO dto = Mockito.mock(RentApplicationDTO.class);
        RentApplication entity = Mockito.mock(RentApplication.class);
        when(entity.getId()).thenReturn(someUUID);

        // Act
        UUID id = uut.create(dto);

        // Assert
        verify(service, times(1)).create(entity);
        Assert.assertEquals(id, someUUID);

    }

    @Test
    public void testDelete() throws Exception {
        // act
        uut.delete(someUUID);

        // assert
        verify(service, times(1)).delete(someUUID);
    }

}