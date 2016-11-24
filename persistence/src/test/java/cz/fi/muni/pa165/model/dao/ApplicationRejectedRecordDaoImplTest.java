/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.model.dao;

import cz.fi.muni.pa165.model.PersonName;
import cz.fi.muni.pa165.model.Role;
import cz.fi.muni.pa165.model.config.PersistenceApplicationContext;
import cz.fi.muni.pa165.model.entity.ApplicationRejectedRecord;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.RentApplication;
import cz.fi.muni.pa165.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

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

    private Car c1;
    private User u1;
    
    private ApplicationRejectedRecord create() throws ParseException{

        String from = "2016-10-15";
        String to = "2016-11-15";        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = formatter.parse(from);
        Date toDate = formatter.parse(to);

        u1 = User.create(PersonName.of("John Smith"), Role.USER, "j.smith@mail.com");
        userDao.create(u1);
        
        c1 = new Car("R2D2", "456", "Manufacturer", "H510Q", 5, new Date());
        carDao.create(c1);

        RentApplication r1 = new RentApplication(c1, u1, "Work trip to Prague", fromDate, toDate);
        rentApplicationDao.create(r1);

        ApplicationRejectedRecord a1 = new ApplicationRejectedRecord(c1, u1, "Car cannot be rent for such long period of time", r1, new Date());
        recordDao.create(a1);
        
        return a1;
    }
    
    @Test
    public void testFindById() throws ParseException{

        ApplicationRejectedRecord a1 = create();
        ApplicationRejectedRecord record = recordDao.findById(a1.getId());
       
        assertEquals(record, a1);
        assertSame(record, a1);    
        
        recordDao.delete(a1);
        assertNull(recordDao.findById(a1.getId()));
    }
    
    @Test
    public void testFindAll() throws ParseException{
        ApplicationRejectedRecord a1 = create();
        List<ApplicationRejectedRecord> list = recordDao.findAll();
        assertTrue(list.contains(a1));
    }
 
    @Test
    public void testFindByCar() throws ParseException{
        ApplicationRejectedRecord a1 =  create();
        List<ApplicationRejectedRecord> records = recordDao.findByCar(c1);
       
        assertTrue(records.contains(a1));    
        
        recordDao.delete(a1);
        assertNull(recordDao.findById(a1.getId()));
    }

    @Test
    public void testFindByUser() throws ParseException{
        
        ApplicationRejectedRecord a1 = create();
        List<ApplicationRejectedRecord> records = recordDao.findByUser(u1);      
       
        assertTrue(records.contains(a1));    
        
        recordDao.delete(a1);
        assertNull(recordDao.findById(a1.getId()));
    }

    @Test
    public void testDelete() throws ParseException{
        
        ApplicationRejectedRecord a1 = create();
        recordDao.delete(a1);

        assertNull(recordDao.findById(a1.getId()));
    }
}
