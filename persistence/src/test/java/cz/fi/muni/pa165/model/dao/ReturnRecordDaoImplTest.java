package cz.fi.muni.pa165.model.dao;

import cz.fi.muni.pa165.model.entity.*;
import cz.fi.muni.pa165.model.PersonName;
import cz.fi.muni.pa165.model.Role;
import cz.fi.muni.pa165.model.config.PersistenceApplicationContext;
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
    private RentRecordDao rentRecordDao;

    @Autowired
    private ApplicationApprovedRecordDao applicationApprovedRecordDao;

    @Autowired
    private RentApplicationDao rentApplicationDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CarDao carDao;

    private ReturnRecord create() {
        Date created = new Date(117,0,20);
        Car car = new Car("R2D2", "456", "Manufacturer", "H510Q", 5, created);
        carDao.create(car);
        User user = new User(PersonName.of("John Doe"), Role.MANAGER, "john.doe@company.com", created);
        userDao.create(user);
        Date from = new Date(117,1,2);
        Date to = new Date(117,1,5);
        RentApplication rentApplication = new RentApplication(car, user, "please give me a car", from, to, created);
        rentApplicationDao.create(rentApplication);
        ApplicationApprovedRecord applicationApprovedRecord = new ApplicationApprovedRecord(car, user, from, to, "sure", rentApplication, new Date(117,0,22));
        applicationApprovedRecordDao.create(applicationApprovedRecord);
        RentRecord rentRecord = new RentRecord(car, user, applicationApprovedRecord, "happy riding!", 25, 10000, new Date(117,1,2));
        rentRecordDao.create(rentRecord);
        ReturnRecord returnRecord = new ReturnRecord(car, user, rentRecord, "car is ok", 5, 10100, new Date(117,1,5));
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
        List<ReturnRecord> retrieved = returnRecordDao.getRecordsCreatedBetween(new Date(117,1,4), new Date(117,1,5));
        assertFalse(retrieved.contains(r));
        returnRecordDao.create(r);

        retrieved = returnRecordDao.getRecordsCreatedBetween(new Date(117,1,4), new Date(117,1,5));
        assertTrue(retrieved.contains(r));
    }
}