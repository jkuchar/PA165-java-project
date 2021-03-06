package cz.fi.muni.pa165.model.dao;

import cz.fi.muni.pa165.model.PersonName;
import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.model.config.PersistenceApplicationContext;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.RentApplication;
import cz.fi.muni.pa165.model.entity.User;
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
        Date created = new Date(117,0,20);
        Car car = new Car("R2D2", "456", "Manufacturer", "H510Q", 5, created);
        carDao.create(car);
        User user = new User(PersonName.of("John Doe"), Role.MANAGER, "john.doe@company.com", created);
        userDao.create(user);
        Date from = new Date(117,1,2);
        Date to = new Date(117,1,5);
        RentApplication rentApplication = new RentApplication(car, user, "please give me a car", from, to, created);
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
        List<RentApplication> retrieved = rentApplicationDao.getRecordsCreatedBetween(new Date(117,0,18), new Date(117,0,23));
        assertFalse(retrieved.contains(r));
        rentApplicationDao.create(r);

        retrieved = rentApplicationDao.getRecordsCreatedBetween(new Date(117,0,18), new Date(117,0,23));
        assertTrue(retrieved.contains(r));
    }
}