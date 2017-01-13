package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.entity.RentApplication;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jkuchar
 */
public interface RentApplicationService {
    
     /**
     * Method to retrieve all rent application records from db.
     * @return all rent application records
     */
    List<RentApplication> findAll();

     /**
     * Method to retrieve rent application record with corresponding id.
     * @param id id of rent application record
     * @return return rent application record with certain id
     */
    RentApplication findById(UUID id);

     /**
     * Method to retrieve rent application records with corresponding car.
     * @param carId is id of certain car
     * @return list of all rent application records with certain car
     */
    List<RentApplication> findByCar(UUID carId);

    /**
     * Method to find all rent application records of certain user.
     * @param userId is id of certain user
     * @return list of all rent application records of certain user
     */    
    List<RentApplication> findByUser(UUID userId);

    /**
     * Method to retrieve rent application records between certain dates.
     * @param from start date
     * @param to end date
     * @return list of all rent application records which were created between certain dates
     */    
    List<RentApplication> getRecordsCreatedBetween(Date from, Date to);

    /**
     * Create new rent application record.
     * @param r is new rent application record
     */    
    void create(RentApplication r);

}
