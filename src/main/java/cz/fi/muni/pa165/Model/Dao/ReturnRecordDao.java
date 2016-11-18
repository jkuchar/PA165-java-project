/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.ReturnRecord;
import cz.fi.muni.pa165.Model.Entity.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public interface ReturnRecordDao {

    /**
     * Method retrieve all return records from db
     * @return all return records
     */
    public List<ReturnRecord> findAll();

    /**
     * Method retrieve item with corresponding id
     * @param id id of return record
     * @return return return record with certain id
     */
    public ReturnRecord findById(UUID id);

    /**
     * Method retrieve return records with corresponding car.
     * @param c certain car
     * @return list of all return records with certain car
     */
    public List<ReturnRecord> findByCar(Car c);

    /**
     * Method to find all return records of certain user.
     * @param u certain user
     * @return list of all return records of certain user
     */
    public List<ReturnRecord> findByUser(User u);

    /**
     * Method retrieve return records betweens certain dates.
     * @param from start date
     * @param to end date
     * @return list of all return records which were created between certain dates
     */
    public List<ReturnRecord> getRecordsCreatedBetween(Date from, Date to);
    
    /**
     * Method insert new return record into db.
     * @param r is new return record
     */
    void create(ReturnRecord r);

    /**
     * Method delete return record from db.
     * @param r is return record to delete
     */
    void delete(ReturnRecord r);
    
}
