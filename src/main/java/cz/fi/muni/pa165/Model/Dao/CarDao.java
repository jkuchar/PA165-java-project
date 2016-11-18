package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.CarState;
import cz.fi.muni.pa165.Model.Entity.Car;

import java.util.List;
import java.util.UUID;

/**
 * Created by SARMIR on 29. 10. 2016.
 */
public interface CarDao {

    /**
     * Method retrieve all cars from db
     * @return List of all cars in db
     */
    List<Car> findAll();

    /**
     * Method retrieve item with corresponding id
     * @param id id of certain car
     * @return return cars with certain id
     */
    Car findById(UUID id);

    /**
     * Method retrieve car with corresponding serial number.
     * @param serialNumber is serial number of certain car
     * @return car with certain serial number
     */
    Car findBySerialNumber(String serialNumber);

    /**
     * Method retrieve car with corresponding plate number.
     * @param regPlateNumber is plate number of certian car
     * @return car with certain plate number
     */
    Car findByRegPlateNumber(String regPlateNumber);

    /**
     * Method retrieve all cars with certain car state from car park perspective.
     * @param state is current state of car.
     * @return all cars with certian car state.
     */
    List<Car> findByState(CarState state);

    /**
     * Method insert new car into db.
     * @param c is new created car
     */
    void create(Car c);

    /**
     * Method delete car from db.
     * @param c is car to be deleted
     */
    void delete(Car c);

    /**
     * Method is used for updating information of car.
     * @param c is car to be updated
     */
    void update(Car c);

}
