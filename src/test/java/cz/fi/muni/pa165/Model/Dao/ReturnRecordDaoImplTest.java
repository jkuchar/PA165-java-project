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
public class ReturnRecordDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private ReturnRecordDao returnRecordDao;

    @Autowired
    private RentApplicationDao rentApplicationDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CarDao carDao;

    private ReturnRecord create() {
        Date created = new Date();
        Car car = new Car("R2D2", "456", "Manufacturer", "H510Q", 5, created);
        carDao.create(car);
        User user = new User(PersonName.of("John Doe"), Role.MANAGER, "john.doe@company.com", created);
        userDao.create(user);
        Date from = new Date(117,1,2);
        Date to = new Date(117,1,5);
        RentApplication rentApplication = new RentApplication(car, user, "please give me a car", from, to);
        ApplicationApprovedRecord approved = new ApplicationApprovedRecord(car, user, from, to, "sure", rentApplication);
        RentRecord rentRecord = new RentRecord(car, user, approved, "happy riding!", 25, 10000);
        ReturnRecord returnRecord = new ReturnRecord(car, user, rentRecord, "car is ok", 5, 10100);
        return returnRecord;
    }

    @Test
    public void testFindAll() {
        ReturnRecord r = create();
        returnRecordDao.create(r);
        List<ReturnRecord> list = returnRecordDao.findAll();
        assertTrue(list.contains(r));
    }

    @Test
    public void testFindById() {
        ReturnRecord r = create();
        assertNull(returnRecordDao.findById(r.getId()));
        returnRecordDao.create(r);

        ReturnRecord retrieved = returnRecordDao.findById(r.getId());
        assertEquals(retrieved, r);
        assertSame(retrieved, r);

        returnRecordDao.delete(r);
        assertNull(returnRecordDao.findById(r.getId()));
    }

    @Test
    public void testFindByCar() {
        ReturnRecord r = create();
        List<ReturnRecord> retrieved = returnRecordDao.findByCar(r.getCar());
        assertFalse(retrieved.contains(r));
        returnRecordDao.create(r);

        retrieved = returnRecordDao.findByCar(r.getCar());
        assertTrue(retrieved.contains(r));
    }

    @Test
    public void testFindByUser() {
        ReturnRecord r = create();
        List<ReturnRecord> retrieved = returnRecordDao.findByUser(r.getUser());
        assertFalse(retrieved.contains(r));
        returnRecordDao.create(r);

        retrieved = returnRecordDao.findByUser(r.getUser());
        assertTrue(retrieved.contains(r));
    }

    @Test
    public void testGetRecordsCreatedBetween() {
        ReturnRecord r = create();
        List<ReturnRecord> retrieved = returnRecordDao.getRecordsCreatedBetween(new Date(117,0,2), new Date(117,0,5));
        assertFalse(retrieved.contains(r));
        returnRecordDao.create(r);

        retrieved = returnRecordDao.getRecordsCreatedBetween(new Date(117,0,2), new Date(117,0,5));
        assertTrue(retrieved.contains(r));
    }

}