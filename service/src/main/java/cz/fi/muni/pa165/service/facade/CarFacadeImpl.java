/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.CarDTO;
import cz.fi.muni.pa165.api.enums.CarState;
import cz.fi.muni.pa165.api.facade.CarFacade;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
@Service
@Transactional
public class CarFacadeImpl implements CarFacade {
    
    private final CarService carService;

    private final BeanMappingService beanMappingService;

    @Autowired
    public CarFacadeImpl(CarService carService, BeanMappingService beanMappingService) {
        this.carService = carService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public UUID createCar(CarDTO car) {
        Car c = new Car(car.getSerialNumber(),car.getRegPlateNumber(),car.getManufacturer(),car.getType(),car.getNumberOfSeats(),car.getEstablishDate());
        carService.createCar(c);
        return  c.getId();
    }

    @Override
    public List<CarDTO> findAllCars() {
        return beanMappingService.mapTo(carService.findAllCars(),CarDTO.class);
    }

    @Override
    public CarDTO findCarById(UUID id) {
        return beanMappingService.mapTo(carService.findCarById(id),CarDTO.class);
    }

    @Override
    public CarDTO findCarBySerialNumber(String serialNumber) {
        return beanMappingService.mapTo(carService.findCarBySerialNumber(serialNumber),CarDTO.class);
    }

    @Override
    public CarDTO findCarByRegPlateNumber(String regPlateNumber) {
        return beanMappingService.mapTo(carService.findCarByRegPlateNumber(regPlateNumber),CarDTO.class);
    }

    @Override
    public List<CarDTO> getAllCarsByState(CarState state) {
        return beanMappingService.mapTo(
                carService.getAllCarsByState(
                        beanMappingService.mapTo(state, cz.fi.muni.pa165.model.CarState.class)
                ),
                CarDTO.class
        );
    }

    @Override
    public List<CarDTO> getAllCarsByManufacturer(String manufacturer) {
        return beanMappingService.mapTo(carService.getAllCarsByManufacturer(manufacturer),CarDTO.class);
    }

    @Override
    public List<CarDTO> getAllCarsByType(String type) {
        return beanMappingService.mapTo(carService.getAllCarsByType(type),CarDTO.class);
    }

    @Override
    public List<CarDTO> getAllCarsBySeats(int seats) {
        return beanMappingService.mapTo(carService.getAllCarsBySeats(seats),CarDTO.class);
    }

    @Override
    public void serviceCar(UUID carId) {
        carService.serviceCar(carService.findCarById(carId));
    }

    @Override
    public void discardCar(UUID carId) {
        carService.discardCar(carService.findCarById(carId));
    }
    
}
