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
public class RentApplicationDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private RentApplicationDao rentApplicationDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CarDao carDao;

    private RentApplication create() {
        Date created = new Date();
        Car car = new Car("R2D2", "456", "Manufacturer", "H510Q", 5, created);
        carDao.create(car);
        User user = new User(PersonName.of("John Doe"), Role.MANAGER, "john.doe@company.com", created);
        userDao.create(user);
        Date from = new Date(117,1,2);
        Date to = new Date(117,1,5);
        RentApplication rentApplication = new RentApplication(car, user, "please give me a car", from, to);
        return rentApplication;
    }

    @Test
    public void testFindAll() {
        RentApplication r = create();
        rentApplicationDao.create(r);
        List<RentApplication> list = rentApplicationDao.findAll();
        assertTrue(list.contains(r));
    }

    @Test
    public void testFindById() {
        RentApplication r = create();
        assertNull(rentApplicationDao.findById(r.getId()));
        rentApplicationDao.create(r);

        RentApplication retrieved = rentApplicationDao.findById(r.getId());
        assertEquals(retrieved, r);
        assertSame(retrieved, r);

        rentApplicationDao.delete(r);
        assertNull(rentApplicationDao.findById(r.getId()));
    }

    @Test
    public void testFindByCar() {
        RentApplication r = create();
        List<RentApplication> retrieved = rentApplicationDao.findByCar(r.getCar());
        assertFalse(retrieved.contains(r));
        rentApplicationDao.create(r);

        retrieved = rentApplicationDao.findByCar(r.getCar());
        assertTrue(retrieved.contains(r));
    }

    @Test
    public void testFindByUser() {
        RentApplication r = create();
        List<RentApplication> retrieved = rentApplicationDao.findByUser(r.getUser());
        assertFalse(retrieved.contains(r));
        rentApplicationDao.create(r);

        retrieved = rentApplicationDao.findByUser(r.getUser());
        assertTrue(retrieved.contains(r));
    }

    @Test
    public void testGetRecordsCreatedBetween() {
        RentApplication r = create();
        List<RentApplication> retrieved = rentApplicationDao.getRecordsCreatedBetween(new Date(117,0,2), new Date(117,0,5));
        assertFalse(retrieved.contains(r));
        rentApplicationDao.create(r);

        retrieved = rentApplicationDao.getRecordsCreatedBetween(new Date(117,0,2), new Date(117,0,5));
        assertTrue(retrieved.contains(r));
    }

}