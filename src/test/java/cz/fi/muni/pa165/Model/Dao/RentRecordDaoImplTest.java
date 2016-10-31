package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.*;
import cz.fi.muni.pa165.Model.PersonName;
import cz.fi.muni.pa165.Model.Role;
import cz.fi.muni.pa165.Model.config.PersistenceApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author rtrembecky
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class RentRecordDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private RentRecordDao rentRecordDao;

    private RentRecord create() {
        Date created = new Date();
        Car car = new Car("R2D2", "456", "Manufacturer", "H510Q", 5, created);
        User user = new User(PersonName.of("John Doe"), Role.MANAGER, "john.doe@company.com", created);
        RentApplication rentApplication = new RentApplication(car, user, "please give me car", new Date(117,1,2), new Date(117,1,5));
        ApplicationApprovedRecord applicationApprovedRecord = new ApplicationApprovedRecord(car, user, new Date(117,1,2), new Date(117,1,5), "sure", rentApplication);
        RentRecord rentRecord = new RentRecord(car, user, applicationApprovedRecord, "happy riding!", 25, 10000);
        return rentRecord;
    }

    @Test
    public void testFindAll() {
        RentRecord r = create();
        rentRecordDao.create(r);
        List<RentRecord> list = rentRecordDao.findAll();
        assertTrue(list.contains(r));
    }

    @Test
    public void testFindById() {
        RentRecord r = create();
        assertNull(rentRecordDao.findById(r.getId()));
        rentRecordDao.create(r);

        RentRecord retrieved = rentRecordDao.findById(r.getId());
        assertEquals(retrieved, r);
        assertSame(retrieved, r);

        rentRecordDao.delete(r);
        assertNull(rentRecordDao.findById(r.getId()));
    }

    @Test
    public void testFindByCar() {
        RentRecord r = create();
        List<RentRecord> retrieved = rentRecordDao.findByCar(r.getCar());
        assertFalse(retrieved.contains(r));
        rentRecordDao.create(r);

        retrieved = rentRecordDao.findByCar(r.getCar());
        assertTrue(retrieved.contains(r));
    }

    @Test
    public void testFindByUser() {
        RentRecord r = create();
        List<RentRecord> retrieved = rentRecordDao.findByUser(r.getUser());
        assertFalse(retrieved.contains(r));
        rentRecordDao.create(r);

        retrieved = rentRecordDao.findByUser(r.getUser());
        assertTrue(retrieved.contains(r));
    }

    @Test
    public void testGetRecordsCreatedBetween() {
        RentRecord r = create();
        List<RentRecord> retrieved = rentRecordDao.getRecordsCreatedBetween(new Date(117,0,2), new Date(117,0,5));
        assertFalse(retrieved.contains(r));
        rentRecordDao.create(r);

        retrieved = rentRecordDao.getRecordsCreatedBetween(new Date(117,0,2), new Date(117,0,5));
        assertTrue(retrieved.contains(r));
    }

}