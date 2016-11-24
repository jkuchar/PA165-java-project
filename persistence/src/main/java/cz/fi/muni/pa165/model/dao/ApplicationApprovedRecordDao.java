/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.model.dao;

import cz.fi.muni.pa165.model.entity.ApplicationApprovedRecord;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public interface ApplicationApprovedRecordDao {

    /**
     * Method to retrieve all application approved records from db.
     * @return all application approved records
     */
    public List<ApplicationApprovedRecord> findAll();

    /**
     * Method to retrieve application approved record with corresponding id.
     * @param id id of application approved record
     * @return return application approved record with certain id
     */
    public ApplicationApprovedRecord findById(UUID id);

    /**
     * Method to retrieve application approved records with corresponding car.
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
     * Method to retrieve application approved records between certain dates.
     * @param from start date
     * @param to end date
     * @return list of all application approved records which were created between certain dates
     */
    public List<ApplicationApprovedRecord> getRecordsCreatedBetween(Date from, Date to);
    
    /**
     * Method to insert new application approved record into db.
     * @param r is new application approved record
     */
    void create(ApplicationApprovedRecord r);

    /**
     * Method to delete application approved record from db.
     * @param r is application approved record to delete
     */
    void delete(ApplicationApprovedRecord r);
    
}
