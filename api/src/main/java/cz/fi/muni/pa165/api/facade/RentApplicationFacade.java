package cz.fi.muni.pa165.api.facade;


import cz.fi.muni.pa165.api.dto.RentApplicationDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *  @author jkuchar
 */
public interface RentApplicationFacade {

    /**
     * Find all rent applcation records
     * @return list of all rent applcation records
     */
    List<RentApplicationDTO> findAll();

    /**
     * Find certain rent application record by id
     * @param id is id of rent application
     * @return rent application record with certain id
     */
    RentApplicationDTO findById(UUID id);

    /**
     * Find all rent application records by car
     * @param carId is id of car
     * @return list of all rent applications records by car
     */
    List<RentApplicationDTO> findByCar(UUID carId);

    /**
     * Find all rent application records by user
     * @param userId is user id
     * @return list of all rent applications records by user
     */
    List<RentApplicationDTO> findByUser(UUID userId);

    /**
     * Retrieve all rent application records of certain user.
     * @param userEmail email of certain user
     * @return list of all rent applications records of certain user
     */
    List<RentApplicationDTO> findByUserEmail(String userEmail);

    /**
     * Find all rent application records created between certain dates
     * @param from start date
     * @param to end date
     * @return list of all rent application records created between certain dates
     */
    List<RentApplicationDTO> getRecordsCreatedBetween(Date from, Date to);

    /**
     * Method to insert new rent application record into db.
     * @param r is new rent application record
     * @return id of created record
     */
    UUID create(RentApplicationDTO r);
}
