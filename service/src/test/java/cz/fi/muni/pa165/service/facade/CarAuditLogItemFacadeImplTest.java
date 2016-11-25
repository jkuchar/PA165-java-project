package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.api.dto.CarAuditLogItemDTO;
import cz.fi.muni.pa165.api.facade.CarAuditLogItemFacade;
import cz.fi.muni.pa165.model.entity.CarAuditLogItem;
import cz.fi.muni.pa165.service.CarAuditLogItemService;
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
 * @author jkuchar
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
public class CarAuditLogItemFacadeImplTest extends AbstractTestNGSpringContextTests {

    private CarAuditLogItemFacade uut;
    private CarAuditLogItemService service;
    private final UUID someUUID = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");

    @Inject
    private BeanMappingService beanMappingService;

    @BeforeMethod
    private void prepare() {
        uut = new CarAuditLogItemFacadeImpl(
                service = Mockito.mock(CarAuditLogItemService.class),
                beanMappingService
        );
    }

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        List<CarAuditLogItem> source = new LinkedList<>();
        source.add(Mockito.mock(CarAuditLogItem.class));

        when(service.findAll()).thenReturn(source);

        // Act
        final List<CarAuditLogItemDTO> collection = uut.findAll();

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new CarAuditLogItemDTO());
    }

    @Test
    public void testFindById() throws Exception {
        // Arrange
        CarAuditLogItem entity = Mockito.mock(CarAuditLogItem.class);
        when(service.findById(someUUID)).thenReturn(entity);

        // Act
        final CarAuditLogItemDTO dto = uut.findById(someUUID);

        // Assert
        Assert.assertEquals(dto, new CarAuditLogItemDTO());
    }

    @Test
    public void testFindByCar() throws Exception {
        // Arrange
        List<CarAuditLogItem> source = new LinkedList<>();
        source.add(Mockito.mock(CarAuditLogItem.class));

        when(service.findByCar(someUUID)).thenReturn(source);

        // Act
        final List<CarAuditLogItemDTO> collection = uut.findByCar(someUUID);

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new CarAuditLogItemDTO());
    }

    @Test
    public void testFindByUser() throws Exception {
        // Arrange
        List<CarAuditLogItem> source = new LinkedList<>();
        source.add(Mockito.mock(CarAuditLogItem.class));

        when(service.findByUser(someUUID)).thenReturn(source);

        // Act
        final List<CarAuditLogItemDTO> collection = uut.findByUser(someUUID);

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new CarAuditLogItemDTO());
    }

    @Test
    public void testGetRecordsCreatedBetween() throws Exception {
        // Arrange
        List<CarAuditLogItem> source = new LinkedList<>();
        source.add(Mockito.mock(CarAuditLogItem.class));

        Date from = new Date("01/01/2016");
        Date to = new Date("01/02/2016");


        when(service.getRecordsCreatedBetween(from, to)).thenReturn(source);

        // Act
        final List<CarAuditLogItemDTO> collection = uut.getRecordsCreatedBetween(from, to);

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new CarAuditLogItemDTO());
    }

}