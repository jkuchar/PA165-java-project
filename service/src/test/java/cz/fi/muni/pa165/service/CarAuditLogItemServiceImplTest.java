package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.config.ServiceTestsConfiguration;
import cz.fi.muni.pa165.model.dao.CarAuditLogItemDao;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.CarAuditLogItem;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
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
 * There is no business logic inside this class --> basically proxy test (behavioral)
 * @author jkuchar
 */
public class CarAuditLogItemServiceImplTest {

    @Mock
    private CarAuditLogItemDao carAuditLogItemDao;

    private CarAuditLogItemService carAuditLogItemService;

    private UUID someUUID = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this); // init Mockito magic!
        carAuditLogItemService = new CarAuditLogItemServiceImpl(carAuditLogItemDao);
    }

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        List<CarAuditLogItem> collection = new LinkedList<>();
        when(carAuditLogItemDao.findAll()).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(carAuditLogItemService.findAll(), collection);
    }

    @Test
    public void testFindByCar() throws Exception {
        // Arrange
        List<CarAuditLogItem> collection = new LinkedList<>();
        when(carAuditLogItemDao.findByCar(someUUID)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(carAuditLogItemService.findByCar(someUUID), collection);
    }

    @Test
    public void testFindByUser() throws Exception {
        // Arrange
        List<CarAuditLogItem> collection = new LinkedList<>();
        when(carAuditLogItemDao.findByUser(someUUID)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(carAuditLogItemService.findByUser(someUUID), collection);
    }

    @Test
    public void testFindById() throws Exception {
        // Arrange
        CarAuditLogItem someItem = Mockito.mock(CarAuditLogItem.class);
        when(carAuditLogItemDao.findById(someUUID)).thenReturn(someItem);

        // Act + Assert
        Assert.assertSame(carAuditLogItemService.findById(someUUID), someItem);
    }

    @Test
    public void testGetRecordsCreatedBetween() throws Exception {
        // Arrange
        List<CarAuditLogItem> collection = new LinkedList<>();
        Date from = new Date("2016-01-01");
        Date to   = new Date("2016-02-01");
        when(carAuditLogItemDao.getRecordsCreatedBetween(from, to)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(carAuditLogItemService.getRecordsCreatedBetween(from, to), collection);
    }

}