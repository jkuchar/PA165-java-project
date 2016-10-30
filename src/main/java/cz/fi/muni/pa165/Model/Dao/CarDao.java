package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.CarState;
import cz.fi.muni.pa165.Model.Entity.Car;

import java.util.List;
import java.util.UUID;

/**
 * Created by SARMIR on 29. 10. 2016.
 */
public interface CarDao {

    List<Car> findAll();
    Car findById(UUID id);
    Car findBySerialNumber(String serialNumber);
    Car findByRegPlateNumber(String regPlateNumber);
    List<Car> findByState(CarState state);

    void insertCar(Car c);
    void updateCar(Car c);
    void deleteCar(Car c);

}
