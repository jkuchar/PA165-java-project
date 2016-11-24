/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.CarDTO;
import cz.fi.muni.pa165.api.enums.CarState;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public interface CarFacade {

    /**
     * Create new car.
     * @param car is new created car.
     * @return id of created car
     */
    UUID createCar(CarDTO car);
        
    /**
     * Find all existing cars.
     * @return all cars
     */
    List<CarDTO> findAllCars();
        
    /**
     * Find car by given id.
     * @param id is ID of certain car
     * @return car with given id
     */
    CarDTO findCarById(UUID id);

    /**
     * Get car by serial number.
     * @param serialNumber is serial number of certain car
     * @return car with certain serial number
     */
    CarDTO findCarBySerialNumber(String serialNumber);

    /**
     * Get car by Plate number.
     * @param regPlateNumber is plate number of certain car
     * @return car with certain plate number
     */
    CarDTO findCarByRegPlateNumber(String regPlateNumber);

    /**
     * Get all cars with the given state.
     * @param state is current state of car.
     * @return all cars with certain car state.
     */
    List<CarDTO> getAllCarsByState(CarState state);
    
     /**
     * Get all cars with the given manufacturer.
     * @param manufacturer is manufacturer of car.
     * @return all cars with certain manufacturer.
     */
    List<CarDTO> getAllCarsByManufacturer(String manufacturer);
    
     /**
     * Get all cars with the given type.
     * @param type is type of car.
     * @return all cars with certain car type.
     */
    List<CarDTO> getAllCarsByType(String type);
    
     /**
     * Get all cars with the number of seats.
     * @param seats is the number of seats.
     * @return all cars with certain number of seats.
     */
    List<CarDTO> getAllCarsBySeats(int seats);
    
    /**
     * Change state of serviced car to servicing. 
     * @param carId is id of serviced car.
     */
    void serviceCar(UUID carId);
    
    /**
     * Change state of car to discarded.
     * @param carId is id of car to be discarded.
     */
    void discardCar(UUID carId);
  
}
