/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.RentRecord;
import cz.fi.muni.pa165.Model.Entity.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public interface RentRecordDao {

    /**
     * Method retrieve all rent records from db
     * @return all rent records
     */
    public List<RentRecord> findAll();

    /**
     * Method retrieve item with corresponding id
     * @param id id of rent record
     * @return return rent record with certain id
     */
    public RentRecord findById(UUID id);

    /**
     * Method retrieve rent records with corresponding car.
     * @param c certain car
     * @return list of all rent records with certain car
     */
    public List<RentRecord> findByCar(Car c);

    /**
     * Method to find all rent records of certain user.
     * @param u certain user
     * @return list of all rent records of certain user
     */
    public List<RentRecord> findByUser(User u);

    /**
     * Method retrieve rent records betweens certain dates.
     * @param from start date
     * @param to end date
     * @return list of all rent records which were created between certain dates
     */
    public List<RentRecord> getRecordsCreatedBetween(Date from, Date to);
    
    /**
     * Method insert new rent record into db.
     * @param r is new rent record
     */
    void create(RentRecord r);

    /**
     * Method delete rent record from db.
     * @param r is rent record to delete
     */
    void delete(RentRecord r);
    
}
