package cz.fi.muni.pa165.model.entity;

import cz.fi.muni.pa165.enums.CarState;
import cz.fi.muni.pa165.model.DomainException;
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
    public void testChangeStateValidTransitions() throws DomainException {
        Car car = create();

        car.changeState(CarState.OK);
        assertEquals(car.getCarState(), CarState.OK);

        car.changeState(CarState.SERVICING);
        assertEquals(car.getCarState(), CarState.SERVICING);

        car.changeState(CarState.SERVICING);
        assertEquals(car.getCarState(), CarState.SERVICING);

        car.changeState(CarState.OK);
        assertEquals(car.getCarState(), CarState.OK);

        car.changeState(CarState.OK);
        assertEquals(car.getCarState(), CarState.OK);

        car.changeState(CarState.DISCARDED);
        assertEquals(car.getCarState(), CarState.DISCARDED);

        car.changeState(CarState.DISCARDED);
        assertEquals(car.getCarState(), CarState.DISCARDED);
    }

    @Test
    public void testChangeStateInvalidTransitions() throws DomainException {
        Car car = create();

        car.changeState(CarState.DISCARDED);
        assertEquals(car.getCarState(), CarState.DISCARDED);

        try {
            car.changeState(CarState.OK);
            fail("Transition from DISCARDED to OK should not be allowed.");
        } catch(DomainException e) {
            assertEquals(car.getCarState(), CarState.DISCARDED);
        }

        try {
            car.changeState(CarState.SERVICING);
            fail("Transition from DISCARDED to SERVICING should not be allowed.");
        } catch(DomainException e) {
            assertEquals(car.getCarState(), CarState.DISCARDED);
        }
    }
}