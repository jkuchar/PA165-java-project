package cz.fi.muni.pa165.model.entity;

import cz.fi.muni.pa165.enums.CarState;
import cz.fi.muni.pa165.model.DomainException;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.fail;

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
        assertEquals(car.getSeats(), 5);
        assertEquals(car.getEstablishDate(), created);

        return car;
    }

    @Test
    public void testChangeStateValidTransitions() throws DomainException {
        Car car = create();

        car.changeState(CarState.OK);
        assertEquals(car.getState(), CarState.OK);

        car.changeState(CarState.SERVICING);
        assertEquals(car.getState(), CarState.SERVICING);

        car.changeState(CarState.SERVICING);
        assertEquals(car.getState(), CarState.SERVICING);

        car.changeState(CarState.OK);
        assertEquals(car.getState(), CarState.OK);

        car.changeState(CarState.OK);
        assertEquals(car.getState(), CarState.OK);

        car.changeState(CarState.DISCARDED);
        assertEquals(car.getState(), CarState.DISCARDED);

        car.changeState(CarState.DISCARDED);
        assertEquals(car.getState(), CarState.DISCARDED);
    }

    @Test
    public void testChangeStateInvalidTransitions() throws DomainException {
        Car car = create();

        car.changeState(CarState.DISCARDED);
        assertEquals(car.getState(), CarState.DISCARDED);

        assertThrows(DomainException.class, () -> car.changeState(CarState.OK));
        assertThrows(DomainException.class, () -> car.changeState(CarState.SERVICING));
    }
}