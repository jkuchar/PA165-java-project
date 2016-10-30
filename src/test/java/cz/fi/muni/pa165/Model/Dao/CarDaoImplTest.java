package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.config.PersistenceApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.validation.constraints.Future;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by SARMIR on 30. 10. 2016.
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class CarDaoImplTest extends AbstractTransactionalTestNGSpringContextTests{

    @Autowired
    private CarDao carDao;

    @Test
    public void testFindAll() throws Exception {

        Car c;
        carDao.create(c = buildCar());

        List<Car> allCars = carDao.findAll();

        assertTrue(allCars.contains(c));
    }

    private Car buildCar() {
        return new Car("5348534251", "LM258896", "Ford", "Focus", 5, new Date());
    }

    @Test
    public void testFindById() throws Exception{

        Car original = buildCar();
        assertNotNull(carDao.findById(original.getID()));
        carDao.create(original);

        {
            Car retrieved = carDao.findById(original.getID());
            assertEquals(retrieved.getSerialNumber(), "5348534251");

            assertEquals(retrieved, original);
            assertSame(retrieved, original);
        }

        carDao.delete(original);

        assertNull(carDao.findById(original.getID()));
    }

    @Test
    public void testFindBySerialNumber() throws Exception {

        Car original = buildCar();
        assertNotNull(carDao.findBySerialNumber(original.getSerialNumber()));

        carDao.create(original);

        {
         Car retrieved = carDao.findBySerialNumber(original.getSerialNumber());
         assertEquals(retrieved.getRegPlateNumber(), "5348534251");

         assertEquals(retrieved, original);
         assertSame(retrieved, original);
        }
    }

    @Test
    public void testFindByRegPlateNumber() throws Exception {

        Car original = buildCar();
        assertNotNull(carDao.findByRegPlateNumber(original.getRegPlateNumber()));

        carDao.create(original);

        {
            Car retrieved = carDao.findByRegPlateNumber(original.getRegPlateNumber());
            assertEquals(retrieved.getRegPlateNumber(), "LM258896");

            assertEquals(retrieved, original);
            assertSame(retrieved, original);
        }
    }

    @Test
    public void testFindByState() throws Exception {

        Car original = buildCar();
        assertNotNull(carDao.findByState(original.getCarState()));

        carDao.create(original);

        {
            List<Car> retrieved = carDao.findByState(original.getCarState());
            assertEquals(1, retrieved.size());

            for (Car cars : retrieved) {
                assertEquals(cars.getCarState(), original.getCarState());
                assertEquals(cars.getSerialNumber(), original.getSerialNumber());
                assertEquals(cars.getRegPlateNumber(), original.getRegPlateNumber());
            }
        }
    }

}
