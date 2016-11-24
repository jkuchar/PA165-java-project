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
public class RentRecordDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

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

    private RentRecord create() {
        Date created = new Date(117,0,20);
        Car car = new Car("R2D2", "456", "Manufacturer", "H510Q", 5, created);
        carDao.create(car);
        User user = new User(PersonName.of("John Doe"), Role.MANAGER, "john.doe@company.com", created);
        userDao.create(user);
        Date from = new Date(117,1,2);
        Date to = new Date(117,1,5);
        RentApplication rentApplication = new RentApplication(car, user, "please give me a car", from, to, created);
        rentApplicationDao.create(rentApplication);
        ApplicationApprovedRecord applicationApprovedRecord = new ApplicationApprovedRecord(car, user, new Date(117,1,2), new Date(117,1,5), "sure", rentApplication, new Date(117,0,22));
        applicationApprovedRecordDao.create(applicationApprovedRecord);
        RentRecord rentRecord = new RentRecord(car, user, applicationApprovedRecord, "happy riding!", 25, 10000, new Date(117,1,2));
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
        List<RentRecord> retrieved = rentRecordDao.getRecordsCreatedBetween(new Date(117,1,2), new Date(117,1,3));
        assertFalse(retrieved.contains(r));
        rentRecordDao.create(r);

        retrieved = rentRecordDao.getRecordsCreatedBetween(new Date(117,1,2), new Date(117,1,3));
        assertTrue(retrieved.contains(r));
    }
}