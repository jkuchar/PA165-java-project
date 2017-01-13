/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.model.PersonName;
import cz.fi.muni.pa165.model.dao.ApplicationApprovedRecordDao;
import cz.fi.muni.pa165.model.dao.ApplicationRejectedRecordDao;
import cz.fi.muni.pa165.model.entity.ApplicationApprovedRecord;
import cz.fi.muni.pa165.model.entity.ApplicationRejectedRecord;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.User;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author charlliz
 */
public class ApplicationRejectedRecordServiceImplTest {

    @InjectMocks
    private ApplicationRejectedRecordService service;

    @Mock
    private ApplicationRejectedRecordDao dao;

    private UUID someUUID;
    private Car car;
    private User user;

    @BeforeMethod
    public void setUp() throws Exception {
        dao = Mockito.mock(ApplicationRejectedRecordDao.class);
        service = new ApplicationRejectedRecordServiceImpl(dao);
        someUUID = UUID.randomUUID();
        Date created = new Date("2016/2/5");
        car = new Car("R2D2", "456", "Manufacturer", "H510Q", 5, created);
        user = new User(PersonName.of("John Doe"), Role.MANAGER, "john.doe@company.com", created);
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<ApplicationRejectedRecord> collection = new LinkedList<>();
        when(dao.findAll()).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(service.findAllRecords(), collection);
    }

    @Test
    public void testFindById() {
        // Arrange
        ApplicationRejectedRecord someItem = Mockito.mock(ApplicationRejectedRecord.class);
        when(dao.findById(someUUID)).thenReturn(someItem);

        // Act + Assert
        Assert.assertSame(service.findRecordById(someUUID), someItem);
    }

    @Test
    public void testFindByCar() {
        // Arrange
        List<ApplicationRejectedRecord> collection = new LinkedList<>();
        when(dao.findByCar(car)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(service.getAllRecordsByCar(car), collection);
    }

    @Test
    public void testFindByUser() {
        // Arrange
        List<ApplicationRejectedRecord> collection = new LinkedList<>();
        when(dao.findByUser(user)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(service.getAllRecordsByUser(user), collection);
    }

    @Test
    public void testGetRecordsCreatedBetween() {
        // Arrange
        List<ApplicationRejectedRecord> collection = new LinkedList<>();
        Date from = new Date("01/01/2016");
        Date to = new Date("01/02/2016");
        when(dao.getRecordsCreatedBetween(from, to)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(service.getAllRecordsCreatedBetween(from, to), collection);
    }

    @Test
    public void testCreate() {
        // Arrange
        ApplicationRejectedRecord rejectedRecord = Mockito.mock(ApplicationRejectedRecord.class);

        // Act
        service.create(rejectedRecord);

        // Assert
        verify(dao, times(1)).create(rejectedRecord);
    }
    
}
