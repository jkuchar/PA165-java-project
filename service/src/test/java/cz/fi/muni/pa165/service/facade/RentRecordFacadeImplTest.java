package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.RentRecordDTO;
import cz.fi.muni.pa165.api.facade.RentRecordFacade;
import cz.fi.muni.pa165.model.entity.RentRecord;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * @author jakubsarmir
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
public class RentRecordFacadeImplTest extends AbstractTestNGSpringContextTests {

    private RentRecordFacade rrf;
    private RentRecordService rrservice;
    private ApplicationApprovedRecordService aars;
    private UserService userService;
    private CarService carService;
    private final UUID someUUID = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");

    @Inject
    private BeanMappingService beanMappingService;

    @BeforeMethod
    private void prepare() {
        rrf = new RentRecordFacadeImpl(
                beanMappingService,
                rrservice = Mockito.mock(RentRecordService.class),
                userService = Mockito.mock(UserService.class),
                carService = Mockito.mock(CarService.class),
                aars = Mockito.mock(ApplicationApprovedRecordService.class)
        );

    }

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        List<RentRecord> source = new LinkedList<>();
        source.add(Mockito.mock(RentRecord.class));

        when(rrservice.findAll()).thenReturn(source);

        // Act
        final List<RentRecordDTO> collection = rrf.findAll();

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new RentRecordDTO());
    }

    @Test
    public void testFindById() throws Exception {
        // Arrange
        RentRecord entity = Mockito.mock(RentRecord.class);
        when(rrservice.findById(someUUID)).thenReturn(entity);

        // Act
        final RentRecordDTO dto = rrf.findById(someUUID);

        // Assert
        Assert.assertEquals(dto, new RentRecordDTO());
    }

    @Test
    public void testFindByCar() throws Exception {
        // Arrange
        List<RentRecord> source = new LinkedList<>();
        source.add(Mockito.mock(RentRecord.class));

        when(rrservice.findByCar(someUUID)).thenReturn(source);

        // Act
        final List<RentRecordDTO> collection = rrf.findByCar(someUUID);

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new RentRecordDTO());
    }

    @Test
    public void testFindByUser() throws Exception {
        // Arrange
        List<RentRecord> source = new LinkedList<>();
        source.add(Mockito.mock(RentRecord.class));

        when(rrservice.findByUser(someUUID)).thenReturn(source);

        // Act
        final List<RentRecordDTO> collection = rrf.findByUser(someUUID);

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new RentRecordDTO());
    }

    @Test
    public void testGetRecordsCreatedBetween() throws Exception {
        // Arrange
        List<RentRecord> source = new LinkedList<>();
        source.add(Mockito.mock(RentRecord.class));

        Date from = new Date("01/01/2016");
        Date to = new Date("01/02/2016");


        when(rrservice.getRecordsCreatedBetween(from, to)).thenReturn(source);

        // Act
        final List<RentRecordDTO> collection = rrf.getRecordsCreatedBetween(from, to);

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new RentRecordDTO());
    }


}
