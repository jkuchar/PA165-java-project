package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.RentRecordDTO;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public interface RentRecordFacade {

    /**
     * Method to retrieve all rent records from db.
     * @return all rent records
     */
    List<RentRecordDTO> findAll();

    /**
     * Method to retrieve rent record with corresponding id.
     * @param id id of rent record
     * @return return rent record with certain id
     */
    RentRecordDTO findById(UUID id);

    /**
     * Method to retrieve rent records with corresponding car.
     * @param carId certain car
     * @return list of all rent records with certain car
     */
    List<RentRecordDTO> findByCar(UUID carId);

    /**
     * Method to find all rent records of certain user.
     * @param userId certain user
     * @return list of all rent records of certain user
     */
    List<RentRecordDTO> findByUser(UUID userId);

    /**
     * Retrieve all rent records of certain user.
     * @param userEmail email of certain user
     * @return list of all rent records of certain user
     */
    List<RentRecordDTO> findByUserEmail(String userEmail);

    /**
     * Method to retrieve rent records between certain dates.
     * @param from start date
     * @param to end date
     * @return list of all rent records which were created between certain dates
     */
    List<RentRecordDTO> getRecordsCreatedBetween(Date from, Date to);

    /**
     * Method to insert new rent record into db.
     * @param r is new rent record
     * @return id of created record
     */
    UUID create(RentRecordDTO r);


}
