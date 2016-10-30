package cz.fi.muni.pa165.Model.Entity;

import cz.fi.muni.pa165.Model.CarState;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.*;

/**
 * @author rtrembecky
 */
public class CarTest {

    private Car create() {
        Date created = new Date();
        Car car = new Car("R2D2", "456", "Manufacturer", "H510Q", 5, created);

        assertEquals(car.getSerialNumber(), "R2D2");
        assertEquals(car.getRegPlateNumber(), "456");
        assertEquals(car.getManufacturer(), "Manufacturer");
        assertEquals(car.getType(), "H510Q");
        assertEquals(car.getNumberOfSeats(), 5);
        assertEquals(car.getEstablishDate(), created);

        return car;
    }

    @Test
    public void testChangeState() throws Exception {
        Car car = create();

        car.changeState(CarState.OK);
        assertEquals(car.getCarState(), CarState.OK);

        car.changeState(CarState.SERVICING);
        assertEquals(car.getCarState(), CarState.SERVICING);

        car.changeState(CarState.OK);
        assertEquals(car.getCarState(), CarState.OK);

        car.changeState(CarState.OK);
        assertEquals(car.getCarState(), CarState.OK);

        car.changeState(CarState.DISCARDED);
        assertEquals(car.getCarState(), CarState.DISCARDED);
    }

    @Test
    public void testSetSerialNumber() throws Exception {
        Car car = create();

        car.setSerialNumber("C3PO");
        assertEquals(car.getSerialNumber(), "C3PO");
    }

    @Test
    public void testSetRegPlateNumber() throws Exception {
        Car car = create();

        car.setRegPlateNumber("000598");
        assertEquals(car.getRegPlateNumber(), "000598");
    }

    @Test
    public void testSetManufacturer() throws Exception {
        Car car = create();

        car.setManufacturer("Škoda Auto");
        assertEquals(car.getManufacturer(), "Škoda Auto");
    }

    @Test
    public void testSetType() throws Exception {
        Car car = create();

        car.setType("top kek");
        assertEquals(car.getType(), "top kek");
    }

    @Test
    public void testSetNumberOfSeats() throws Exception {
        Car car = create();

        car.setNumberOfSeats(3);
        assertEquals(car.getNumberOfSeats(), 3);
    }

}