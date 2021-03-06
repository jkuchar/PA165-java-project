package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.model.PersonName;
import cz.fi.muni.pa165.model.dao.ApplicationApprovedRecordDao;
import cz.fi.muni.pa165.model.entity.ApplicationApprovedRecord;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author rtrembecky
 */
public class ApplicationApprovedRecordServiceImplTest {

    @InjectMocks
    private ApplicationApprovedRecordService service;

    @Mock
    private ApplicationApprovedRecordDao dao;

    private UUID someUUID;
    private Car car;
    private User user;

    @BeforeMethod
    public void setUp() throws Exception {
        dao = Mockito.mock(ApplicationApprovedRecordDao.class);
        service = new ApplicationApprovedRecordServiceImpl(dao);
        someUUID = UUID.randomUUID();
        Date created = new Date("2016/2/5");
        car = new Car("R2D2", "456", "Manufacturer", "H510Q", 5, created);
        user = new User(PersonName.of("John Doe"), Role.MANAGER, "john.doe@company.com", created);
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<ApplicationApprovedRecord> collection = new LinkedList<>();
        when(dao.findAll()).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(service.findAll(), collection);
    }

    @Test
    public void testFindById() {
        // Arrange
        ApplicationApprovedRecord someItem = Mockito.mock(ApplicationApprovedRecord.class);
        when(dao.findById(someUUID)).thenReturn(someItem);

        // Act + Assert
        Assert.assertSame(service.findById(someUUID), someItem);
    }

    @Test
    public void testFindByCar() {
        // Arrange
        List<ApplicationApprovedRecord> collection = new LinkedList<>();
        when(dao.findByCar(car)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(service.findByCar(car), collection);
    }

    @Test
    public void testFindByUser() {
        // Arrange
        List<ApplicationApprovedRecord> collection = new LinkedList<>();
        when(dao.findByUser(user)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(service.findByUser(user), collection);
    }

    @Test
    public void testGetRecordsCreatedBetween() {
        // Arrange
        List<ApplicationApprovedRecord> collection = new LinkedList<>();
        Date from = new Date("01/01/2016");
        Date to = new Date("01/02/2016");
        when(dao.getRecordsCreatedBetween(from, to)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(service.getRecordsCreatedBetween(from, to), collection);
    }

    @Test
    public void testCreate() {
        // Arrange
        ApplicationApprovedRecord approvedRecord = Mockito.mock(ApplicationApprovedRecord.class);

        // Act
        service.create(approvedRecord);

        // Assert
        verify(dao, times(1)).create(approvedRecord);
    }

}