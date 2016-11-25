package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.dao.ReturnRecordDao;
import cz.fi.muni.pa165.model.entity.ReturnRecord;
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
public class ReturnRecordServiceImplTest {

    private ReturnRecordService uut; // unit under test

    private ReturnRecordDao dao;

    private final UUID someUUID = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");

    @BeforeMethod
    public void setUp() throws Exception {
        dao = Mockito.mock(ReturnRecordDao.class);
        uut = new ReturnRecordServiceImpl(dao);
    }

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        List<ReturnRecord> collection = new LinkedList<>();
        when(dao.findAll()).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(uut.findAll(), collection);
    }

    @Test
    public void testFindById() throws Exception {
        // Arrange
        ReturnRecord someItem = Mockito.mock(ReturnRecord.class);
        when(dao.findById(someUUID)).thenReturn(someItem);

        // Act + Assert
        Assert.assertSame(uut.findById(someUUID), someItem);
    }

    @Test
    public void testFindByCar() throws Exception {
        // Arrange
        List<ReturnRecord> collection = new LinkedList<>();
        when(dao.findByCar(someUUID)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(uut.findByCar(someUUID), collection);
    }

    @Test
    public void testFindByUser() throws Exception {
        // Arrange
        List<ReturnRecord> collection = new LinkedList<>();
        when(dao.findByUser(someUUID)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(uut.findByUser(someUUID), collection);
    }

    @Test
    public void testGetRecordsCreatedBetween() throws Exception {
        // Arrange
        List<ReturnRecord> collection = new LinkedList<>();
        Date from = new Date("01/01/2016");
        Date to = new Date("01/02/2016");
        when(dao.getRecordsCreatedBetween(from, to)).thenReturn(collection);

        // Act + Assert
        Assert.assertSame(uut.getRecordsCreatedBetween(from, to), collection);
    }

    @Test
    public void testCreate() throws Exception {
        // Arrange
        ReturnRecord returnRecord = Mockito.mock(ReturnRecord.class);

        // Act
        uut.create(returnRecord);

        // Assert
        verify(dao, times(1)).create(returnRecord);
    }


}
