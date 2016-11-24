/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.entity.ApplicationRejectedRecord;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public interface ApplicationRejectedRecordService {

    /**
     * Method create new application rejected record. 
     * @param r is new application rejected record
     */
    void create(ApplicationRejectedRecord r);
    
    /**
     * Get all application rejected records.
     * @return all application rejected records
     */
    public List<ApplicationRejectedRecord> findAllRecords();

    /**
     * Find application rejected record with corresponding id.
     * @param id id of application rejected record
     * @return return application rejected record with certain id
     */
    public ApplicationRejectedRecord findRecordById(UUID id);

    /**
     * Get all application rejected records with corresponding car.
     * @param c certain car
     * @return list of all application rejected records with certain car
     */
    public List<ApplicationRejectedRecord> getAllRecordsByCar(Car c);

    /**
     * Get all application rejected records of certain user.
     * @param u certain user
     * @return list of all application rejected records of certain user
     */
    public List<ApplicationRejectedRecord> getAllRecordsByUser(User u);

     /**
     * Get all application rejected records between certain dates.
     * @param from start date
     * @param to end date
     * @return list of all application rejected records which were created between certain dates
     */
    public List<ApplicationRejectedRecord> getAllRecordsCreatedBetween(Date from, Date to);
    
}
