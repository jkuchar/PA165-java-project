/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;


import cz.fi.muni.pa165.model.dao.CarDao;
import cz.fi.muni.pa165.model.DomainException;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.enums.CarState;
import cz.fi.muni.pa165.exceptions.CarParkServiceException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author charlliz
 */
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;
    
    @Override
    public void createCar(Car car) {
        carDao.create(car);
    }

    @Override
    public List<Car> findAllCars() {
        return carDao.findAll();
    }

    @Override
    public Car findCarById(UUID id) {
        return carDao.findById(id);
    }

    @Override
    public Car findCarBySerialNumber(String serialNumber) {
        return carDao.findBySerialNumber(null);
    }

    @Override
    public Car findCarByRegPlateNumber(String regPlateNumber) {
        return carDao.findByRegPlateNumber(regPlateNumber);
    }

    @Override
    public List<Car> getAllCarsByState(CarState state) {
        return carDao.findByState(state);
    }

    @Override
    public List<Car> getAllCarsByManufacturer(String manufacturer) {
        return carDao.getAllCarsByManufacturer(manufacturer);
    }

    @Override
    public List<Car> getAllCarsByType(String type) {
        return carDao.getAllCarsByType(type);
    }

    @Override
    public List<Car> getAllCarsBySeats(int seats) {
        return carDao.getAllCarsBySeats(seats);
    }

    @Override
    public void serviceCar(Car car) {
        try{
            car.changeState(CarState.SERVICING);
        }catch(DomainException e){
            throw new CarParkServiceException(e);
        }
    }

    @Override
    public void discardCar(Car car) {
        try{
            car.changeState(CarState.DISCARDED);
        }catch(DomainException e){
            throw new CarParkServiceException(e);
        }
    }
    
}
