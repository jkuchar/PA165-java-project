/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.ApplicationApprovedRecord;
import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public interface ApplicationApprovedRecordDao {

    /**
     * Method retrieve all application approved records from db
     * @return all application approved records
     */
    public List<ApplicationApprovedRecord> findAll();

    /**
     * Method retrieve item with corresponding id
     * @param id id of application approved record
     * @return return application approved record with certain id
     */
    public ApplicationApprovedRecord findById(UUID id);

    /**
     * Method retrieve application approved records with corresponding car.
     * @param c certain car
     * @return list of all application approved records with certain car
     */
    public List<ApplicationApprovedRecord> findByCar(Car c);

    /**
     * Method to find all application approved records of certain user.
     * @param u certain user
     * @return list of all application approved records of certain user
     */
    public List<ApplicationApprovedRecord> findByUser(User u);

    /**
     * Method retrieve application approved records betweens certain dates.
     * @param from start date
     * @param to end date
     * @return list of all application approved records which were created between certain dates
     */
    public List<ApplicationApprovedRecord> getRecordsCreatedBetween(Date from, Date to);
    
    /**
     * Method insert new application approved record into db.
     * @param r is new application approved record
     */
    void create(ApplicationApprovedRecord r);

    /**
     * Method delete application approved record from db.
     * @param r is application approved record to delete
     */
    void delete(ApplicationApprovedRecord r);
    
}
