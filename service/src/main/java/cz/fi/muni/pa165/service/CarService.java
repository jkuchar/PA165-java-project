/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;


import cz.fi.muni.pa165.model.CarState;
import cz.fi.muni.pa165.model.entity.Car;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public interface CarService {
    
    /**
     * Create new car.
     * @param car is new created car.
     */
    void createCar(Car car);
        
    /**
     * Find all existing cars.
     * @return all cars
     */
    List<Car> findAllCars();
        
    /**
     * Find car by given id.
     * @param id is ID of certain car
     * @return car with given id
     */
    Car findCarById(UUID id);

    /**
     * Get car by serial number.
     * @param serialNumber is serial number of certain car
     * @return car with certain serial number
     */
    Car findCarBySerialNumber(String serialNumber);

    /**
     * Get car by Plate number.
     * @param regPlateNumber is plate number of certain car
     * @return car with certain plate number
     */
    Car findCarByRegPlateNumber(String regPlateNumber);

    /**
     * Get all cars with the given state.
     * @param state is current state of car.
     * @return all cars with certain car state.
     */
    List<Car> getAllCarsByState(CarState state);
    
     /**
     * Get all cars with the given manufacturer.
     * @param manufacturer is manufacturer of car.
     * @return all cars with certain manufacturer.
     */
    List<Car> getAllCarsByManufacturer(String manufacturer);
    
     /**
     * Get all cars with the given type.
     * @param type is type of car.
     * @return all cars with certain car type.
     */
    List<Car> getAllCarsByType(String type);
    
     /**
     * Get all cars with the number of seats.
     * @param seats is the number of seats.
     * @return all cars with certain number of seats.
     */
    List<Car> getAllCarsBySeats(int seats);
    
    /**
     * Change state of serviced car to servicing. 
     * @param car is serviced car.
     */
    void serviceCar(Car car);
    
    /**
     * Change state of car to discarded.
     * @param car is car to be discarded.
     */
    void discardCar(Car car);
	    
}
