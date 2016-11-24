/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.ApplicationRejectedRecordDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public interface ApplicationRejectedRecordFacade {

    /**
     * Method create new application rejected record. 
     * @param r is new application rejected record
     * @return id of created record
     */
    UUID create(ApplicationRejectedRecordDTO r);
    
    /**
     * Get all application rejected records.
     * @return all application rejected records
     */
    public List<ApplicationRejectedRecordDTO> findAllRecords();

    /**
     * Find application rejected record with corresponding id.
     * @param id id of application rejected record
     * @return return application rejected record with certain id
     */
    public ApplicationRejectedRecordDTO findRecordById(UUID id);

    /**
     * Get all application rejected records with corresponding car.
     * @param carId id of certain car
     * @return list of all application rejected records with certain car
     */
    public List<ApplicationRejectedRecordDTO> getAllRecordsByCar(UUID carId);

    /**
     * Get all application rejected records of certain user.
     * @param userId id of certain user
     * @return list of all application rejected records of certain user
     */
    public List<ApplicationRejectedRecordDTO> getAllRecordsByUser(UUID userId);

     /**
     * Get all application rejected records between certain dates.
     * @param from start date
     * @param to end date
     * @return list of all application rejected records which were created between certain dates
     */
    public List<ApplicationRejectedRecordDTO> getAllRecordsCreatedBetween(Date from, Date to);
     
}
