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
    
    private ApplicationRejectedRecord a1;
    private ApplicationRejectedRecord a2;
    private User u1;
    private Car c1;

    @BeforeSuite
    public void initTest() throws Exception {

        // Spring bug: SPR-4072: https://jira.spring.io/browse/SPR-4072
        // workaround: http://stackoverflow.com/questions/5192562/spring-autowiring-happens-after-beforeclass-when-running-test-with-maven-surefi
        if(this.recordDao == null) { // @BeforeSuite is executed before autowiring happens
            this.springTestContextPrepareTestInstance();
        }

        String from = "2016-10-15";
        String to = "2016-11-15";        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = formatter.parse(from);
        Date toDate = formatter.parse(to);
        
        u1 = User.create(PersonName.of("John Smith"), Role.USER, "j.smith@mail.com");
        c1 = new Car("R2D2", "456", "Manufacturer", "H510Q", 5, new Date());
        RentApplication r1 = new RentApplication(c1,u1,"Work trip to Prague",fromDate, toDate);
        
        a1 = new ApplicationRejectedRecord(c1,u1,new Date(),"Car cannot be rent for such long period of time",r1);
    
        User u2 = User.create(PersonName.of("Jane Austin"), Role.USER, "j.austin@mail.com");
        Car c2 = new Car("R1D1", "467", "Manufacturer", "H510Q", 5, new Date());
        RentApplication r2 = new RentApplication(c2,u2,"Work trip to Bratislava",fromDate, toDate);
        
        a2 = new ApplicationRejectedRecord(c1,u1,new Date(),"Car has planned service control in that time",r2);
    
        recordDao.create(a1);
        recordDao.create(a2);
    }

    @Test
    public void testCreateRecord() {
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
    public void testFindById() {
        ApplicationRejectedRecord record = recordDao.findById(a1.getId());
       
        assertEquals(a1, record);
        assertEquals(a1.getCar(), record.getCar());
        assertEquals(a1.getComment(), record.getComment());
        assertEquals(a1.getCreated(), record.getCreated());
        assertEquals(a1.getUser(), record.getUser());
        assertEquals(a1.getApplication(), record.getApplication());
        
    }

    @Test
    public void testFindByCar() {
        List<ApplicationRejectedRecord> records = recordDao.findByCar(c1);
       
        assertEquals(1, records.size());
        
        assertEquals(a1, records.get(0));
        assertEquals(a1.getComment(), records.get(0).getComment());
        assertEquals(a1.getCreated(), records.get(0).getCreated());
        assertEquals(a1.getUser(), records.get(0).getUser());
        assertEquals(a1.getApplication(), records.get(0).getApplication());
    }

    @Test
    public void testFindByUser() {
        List<ApplicationRejectedRecord> records = recordDao.findByUser(u1);
       
        assertEquals(1, records.size());
        
        assertEquals(a1, records.get(0));
        assertEquals(a1.getCar(), records.get(0).getCar());
        assertEquals(a1.getComment(), records.get(0).getComment());
        assertEquals(a1.getCreated(), records.get(0).getCreated());
        assertEquals(a1.getApplication(), records.get(0).getApplication());
    }

    @Test
    public void testDelete() {
       recordDao.delete(a1);
       assertEquals(1, recordDao.findAll().size());
       assertNull(recordDao.findById(a1.getId()));
    }
    
}
