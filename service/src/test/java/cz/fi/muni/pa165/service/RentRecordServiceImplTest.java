package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.dao.RentRecordDao;
import cz.fi.muni.pa165.model.entity.RentRecord;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
public class RentRecordServiceImplTest {

    private RentRecordService uut; // unit under test

    private RentRecordDao dao;

    private final UUID someUUID = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");

    @BeforeMethod
    public void setUp() throws Exception {
        dao = Mockito.mock(RentRecordDao.class);
        uut = new RentRecordServiceImpl(dao);
    }

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        List<RentRecord> collection = new LinkedList<>();
        when(dao.findAll()).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(uut.findAll(), collection);
    }

    @Test
    public void testFindById() throws Exception {
        // Arrange
        RentRecord someItem = Mockito.mock(RentRecord.class);
        when(dao.findById(someUUID)).thenReturn(someItem);

        // Act + Assert
        Assert.assertSame(uut.findById(someUUID), someItem);
    }

    @Test
    public void testFindByCar() throws Exception {
        // Arrange
        List<RentRecord> collection = new LinkedList<>();
        when(dao.findByCar(someUUID)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(uut.findByCar(someUUID), collection);
    }

    @Test
    public void testFindByUser() throws Exception {
        // Arrange
        List<RentRecord> collection = new LinkedList<>();
        when(dao.findByUser(someUUID)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(uut.findByUser(someUUID), collection);
    }

    @Test
    public void testGetRecordsCreatedBetween() throws Exception {
        // Arrange
        List<RentRecord> collection = new LinkedList<>();
        Date from = new Date("01/01/2016");
        Date to = new Date("01/02/2016");
        when(dao.getRecordsCreatedBetween(from, to)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(uut.getRecordsCreatedBetween(from, to), collection);
    }

    @Test
    public void testCreate() throws Exception {
        // Arrange
        RentRecord rentRecord = Mockito.mock(RentRecord.class);

        // Act
        uut.create(rentRecord);

        // Assert
        verify(dao, times(1)).create(rentRecord);
    }

}
