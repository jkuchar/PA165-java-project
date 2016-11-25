/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.model.dao;

import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.RentRecord;
import cz.fi.muni.pa165.model.entity.ReturnRecord;
import cz.fi.muni.pa165.model.entity.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public interface ReturnRecordDao {

    /**
     * Method to retrieve all return records from db.
     * @return all return records
     */
    public List<ReturnRecord> findAll();

    /**
     * Method to retrieve return record with corresponding id.
     * @param id id of return record
     * @return return return record with certain id
     */
    public ReturnRecord findById(UUID id);

    /**
     * Method to retrieve return records with corresponding car.
     * @param carId certain car
     * @return list of all return records with certain car
     */
    public List<ReturnRecord> findByCar(UUID carId);

    @Deprecated
    public List<ReturnRecord> findByCar(Car c);

    /**
     * Method to find all return records of certain user.
     * @param userId certain user
     * @return list of all return records of certain user
     */
    public List<ReturnRecord> findByUser(UUID userId);

    @Deprecated
    public List<ReturnRecord> findByUser(User u);

    /**
     * Method to retrieve return records between certain dates.
     * @param from start date
     * @param to end date
     * @return list of all return records which were created between certain dates
     */
    public List<ReturnRecord> getRecordsCreatedBetween(Date from, Date to);
    
    /**
     * Method to insert new return record into db.
     * @param r is new return record
     */
    void create(ReturnRecord r);

    /**
     * Method delete return record from db.
     * @param r is return record to delete
     */
    void delete(ReturnRecord r);
    
}
