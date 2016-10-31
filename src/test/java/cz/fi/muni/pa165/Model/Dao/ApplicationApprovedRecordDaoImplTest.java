package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.ApplicationApprovedRecord;
import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.RentApplication;
import cz.fi.muni.pa165.Model.Entity.User;
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
public class ApplicationApprovedRecordDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private ApplicationApprovedRecordDao applicationApprovedRecordDao;

    @Autowired
    private RentApplicationDao rentApplicationDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CarDao carDao;

    private ApplicationApprovedRecord create() {
        Date created = new Date();
        Car car = new Car("R2D2", "456", "Manufacturer", "H510Q", 5, created);
        carDao.create(car);
        User user = new User(PersonName.of("John Doe"), Role.MANAGER, "john.doe@company.com", created);
        userDao.create(user);
        Date from = new Date(117,1,2);
        Date to = new Date(117,1,5);
        RentApplication rentApplication = new RentApplication(car, user, "please give me car", from, to);
        rentApplicationDao.create(rentApplication);
        ApplicationApprovedRecord applicationApprovedRecord = new ApplicationApprovedRecord(car, user, from, to, "sure", rentApplication);
        return applicationApprovedRecord;
    }
/*
    @Test
    public void testFindAll() {
        ApplicationApprovedRecord r = create();
        applicationApprovedRecordDao.create(r);
        List<ApplicationApprovedRecord> list = applicationApprovedRecordDao.findAll();
        assertTrue(list.contains(r));
    }

    @Test
    public void testFindById() {
        ApplicationApprovedRecord r = create();
        assertNull(applicationApprovedRecordDao.findById(r.getId()));
        applicationApprovedRecordDao.create(r);

        ApplicationApprovedRecord retrieved = applicationApprovedRecordDao.findById(r.getId());
        assertEquals(retrieved, r);
        assertSame(retrieved, r);

        applicationApprovedRecordDao.delete(r);
        assertNull(applicationApprovedRecordDao.findById(r.getId()));
    }

    @Test
    public void testFindByCar() {
        ApplicationApprovedRecord r = create();
        List<ApplicationApprovedRecord> retrieved = applicationApprovedRecordDao.findByCar(r.getCar());
        assertFalse(retrieved.contains(r));
        applicationApprovedRecordDao.create(r);

        retrieved = applicationApprovedRecordDao.findByCar(r.getCar());
        assertTrue(retrieved.contains(r));
    }

    @Test
    public void testFindByUser() {
        ApplicationApprovedRecord r = create();
        List<ApplicationApprovedRecord> retrieved = applicationApprovedRecordDao.findByUser(r.getUser());
        assertFalse(retrieved.contains(r));
        applicationApprovedRecordDao.create(r);

        retrieved = applicationApprovedRecordDao.findByUser(r.getUser());
        assertTrue(retrieved.contains(r));
    }

    @Test
    public void testGetRecordsCreatedBetween() {
        ApplicationApprovedRecord r = create();
        List<ApplicationApprovedRecord> retrieved = applicationApprovedRecordDao.getRecordsCreatedBetween(new Date(117,0,2), new Date(117,0,5));
        assertFalse(retrieved.contains(r));
        applicationApprovedRecordDao.create(r);

        retrieved = applicationApprovedRecordDao.getRecordsCreatedBetween(new Date(117,0,2), new Date(117,0,5));
        assertTrue(retrieved.contains(r));
    }
*/
}