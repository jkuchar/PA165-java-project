/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.ApplicationRejectedRecord;
import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.RentApplication;
import cz.fi.muni.pa165.Model.Entity.User;
import cz.fi.muni.pa165.Model.PersonName;
import cz.fi.muni.pa165.Model.Role;
import cz.fi.muni.pa165.Model.config.PersistenceApplicationContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author charlliz
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class ApplicationRejectedRecordDaoImplTest extends AbstractTransactionalTestNGSpringContextTests  {

    @Autowired
    private ApplicationRejectedRecordDao recordDao;

    // required for fixtures set-up
    @Autowired
    private UserDao userDao;

    // required for fixtures set-up
    @Autowired
    private CarDao carDao;

    // required for fixtures set-up
    @Autowired
    private RentApplicationDao rentApplicationDao;
    
    private ApplicationRejectedRecord a1;
    private ApplicationRejectedRecord a2;
    private User u1;
    private Car c1;

    private static boolean initialized = false;

    private void initTest() throws Exception {
        // execute only once: @BeforeSuite simulation
        // workaround for bug: https://jira.spring.io/browse/SPR-4072
        if(initialized) {
            return;
        }
        initialized = true;


        String from = "2016-10-15";
        String to = "2016-11-15";        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = formatter.parse(from);
        Date toDate = formatter.parse(to);

        userDao.create(u1 = User.create(PersonName.of("John Smith"), Role.USER, "j.smith@mail.com"));
        carDao.create(c1 = new Car("R2D2", "456", "Manufacturer", "H510Q", 5, new Date()));
        RentApplication r1;
        rentApplicationDao.create(r1 = new RentApplication(c1, u1, "Work trip to Prague", fromDate, toDate));

        recordDao.create(a1 = new ApplicationRejectedRecord(c1, u1, new Date(), "Car cannot be rent for such long period of time", r1));

        User u2;
        userDao.create(u2 = User.create(PersonName.of("Jane Austin"), Role.USER, "j.austin@mail.com"));

        Car c2;
        carDao.create(c2 = new Car("R1D1", "467", "Manufacturer", "H510Q", 5, new Date()));

        RentApplication r2;
        rentApplicationDao.create(r2 = new RentApplication(c2, u2, "Work trip to Bratislava", fromDate, toDate));

        recordDao.create(a2 = new ApplicationRejectedRecord(c1, u1, new Date(), "Car has planned service control in that time", r2));
    }

    @Test
    public void testCreateRecord() throws Exception {
        this.initTest();

        List<ApplicationRejectedRecord> records = new ArrayList<>(recordDao.findAll());

        assertEquals(2, records.size());
        
        assertEquals(a1, records.get(0));
        assertEquals(a1.getCar(), records.get(0).getCar());
        assertEquals(a1.getComment(), records.get(0).getComment());
        assertEquals(a1.getCreated(), records.get(0).getCreated());
        assertEquals(a1.getUser(), records.get(0).getUser());
        assertEquals(a1.getApplication(), records.get(0).getApplication());
    }
    
    @Test
    public void testFindById() throws Exception {
        this.initTest();

        ApplicationRejectedRecord record = recordDao.findById(a1.getId());
       
        assertEquals(a1, record);
        assertEquals(a1.getCar(), record.getCar());
        assertEquals(a1.getComment(), record.getComment());
        assertEquals(a1.getCreated(), record.getCreated());
        assertEquals(a1.getUser(), record.getUser());
        assertEquals(a1.getApplication(), record.getApplication());
        
    }

    @Test
    public void testFindByCar() throws Exception {
        this.initTest();

        List<ApplicationRejectedRecord> records = recordDao.findByCar(c1);
       
        assertEquals(1, records.size());
        
        assertEquals(a1, records.get(0));
        assertEquals(a1.getComment(), records.get(0).getComment());
        assertEquals(a1.getCreated(), records.get(0).getCreated());
        assertEquals(a1.getUser(), records.get(0).getUser());
        assertEquals(a1.getApplication(), records.get(0).getApplication());
    }

    @Test
    public void testFindByUser() throws Exception {
        this.initTest();

        List<ApplicationRejectedRecord> records = recordDao.findByUser(u1);
       
        assertEquals(1, records.size());
        
        assertEquals(a1, records.get(0));
        assertEquals(a1.getCar(), records.get(0).getCar());
        assertEquals(a1.getComment(), records.get(0).getComment());
        assertEquals(a1.getCreated(), records.get(0).getCreated());
        assertEquals(a1.getApplication(), records.get(0).getApplication());
    }

    @Test
    public void testDelete() throws Exception {
        this.initTest();

        recordDao.delete(a1);

        assertNull(recordDao.findById(a1.getId()));
    }
    
}
