package cz.fi.muni.pa165.model.dao;

import cz.fi.muni.pa165.enums.CarState;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.config.PersistenceApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.testng.Assert.*;

/**
 * Created by SARMIR on 30. 10. 2016.
 * @author jakubsarmir
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

    private static int buildCar_i = 0;
    private Car buildCar() {
        buildCar_i++;
        return new Car(
            "5348534251" + buildCar_i, // need uniqueness
            "LM258896" + buildCar_i,
            "Ford",
            "Focus",
            5,
            new Date()
        );
    }

    @Test
    public void testFindById() throws Exception{

        Car original = buildCar();
        UUID id = original.getId();

        assertNull(carDao.findById(id));

        carDao.create(original);

        {
            Car retrieved = carDao.findById(id);
            assertEquals(retrieved.getSerialNumber(), original.getSerialNumber());

            assertEquals(retrieved, original);
            assertSame(retrieved, original);
        }

        carDao.delete(original);

        assertNull(carDao.findById(id));
    }

    @Test
    public void testFindBySerialNumber() throws Exception {

        Car original = buildCar();
        String serialNumber = original.getSerialNumber();

        assertNull(carDao.findBySerialNumber(serialNumber));

        carDao.create(original);

        {
            Car retrieved = carDao.findBySerialNumber(serialNumber);

            assertEquals(retrieved.getSerialNumber(), serialNumber);

            assertEquals(retrieved, original);
            assertSame(retrieved, original);
        }
    }

    @Test
    public void testFindByRegPlateNumber() throws Exception {

        Car original = buildCar();
        String regPlateNumber = original.getRegPlateNumber();

        assertNull(carDao.findByRegPlateNumber(regPlateNumber));

        carDao.create(original);

        {
            Car retrieved = carDao.findByRegPlateNumber(regPlateNumber);
            assertEquals(retrieved.getRegPlateNumber(), regPlateNumber);

            assertEquals(retrieved, original);
            assertSame(retrieved, original);
        }
    }

    @Test
    public void testFindByState() throws Exception {

        Car original = buildCar();
        CarState carState = original.getCarState();

        {
            List<Car> carsInState = carDao.findByState(carState);
            assertFalse(carsInState.contains(original));
        }

        carDao.create(original);

        {
            List<Car> retrieved = carDao.findByState(carState);
            assertTrue(retrieved.contains(original));
        }
    }

}
