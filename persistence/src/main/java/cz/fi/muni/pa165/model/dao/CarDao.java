package cz.fi.muni.pa165.model.dao;

import cz.fi.muni.pa165.enums.CarState;
import cz.fi.muni.pa165.model.entity.Car;

import java.util.List;
import java.util.UUID;

/**
 * Created by SARMIR on 29. 10. 2016.
 * @author jakubsarmir
 */
public interface CarDao {

    /**
     * Method to retrieve all cars from db.
     * @return List of all cars in db
     */
    List<Car> findAll();

    /**
     * Method to retrieve car with corresponding id.
     * @param id id of certain car
     * @return return cars with certain id
     */
    Car findById(UUID id);

    /**
     * Method to retrieve car with corresponding serial number.
     * @param serialNumber is serial number of certain car
     * @return car with certain serial number
     */
    Car findBySerialNumber(String serialNumber);

    /**
     * Method to retrieve car with corresponding plate number.
     * @param regPlateNumber is plate number of certain car
     * @return car with certain plate number
     */
    Car findByRegPlateNumber(String regPlateNumber);

    /**
     * Method to retrieve all cars with certain car state from car park perspective.
     * @param state is current state of car.
     * @return all cars with certain car state.
     */
    List<Car> findByState(CarState state);
    /**
     * Method to retrieve all cars with the given manufacturer.
     * @param manufacturer is manufacturer of car.
     * @return all cars with certain manufacturer.
     */
    List<Car> getAllCarsByManufacturer(String manufacturer);
    
     /**
     * Method to retrieve all cars with the given type.
     * @param type is type of car.
     * @return all cars with certain car type.
     */
    List<Car> getAllCarsByType(String type);
    
     /**
     * Method retrieve all cars with the number of seats.
     * @param seats is the number of seats.
     * @return all cars with certain number of seats.
     */
    List<Car> getAllCarsBySeats(int seats);
    
    /**
     * Method to insert new car into db.
     * @param c is new created car
     */
    void create(Car c);

    /**
     * Method to delete car from db.
     * @param c is car to be deleted
     */
    void delete(Car c);

    /**
     * Method to update information about car in db.
     * @param c is car to be updated
     */
    void update(Car c);

}
