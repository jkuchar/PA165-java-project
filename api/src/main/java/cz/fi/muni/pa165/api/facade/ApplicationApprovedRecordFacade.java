package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.ApplicationApprovedRecordDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author rtrembecky
 */
public interface ApplicationApprovedRecordFacade {
    /**
     * Create new application approved record.
     * @param r is new application approved record
     * @return id of created record
     */
    UUID create(ApplicationApprovedRecordDTO r);

    /**
     * Retrieve all application approved records.
     * @return all application approved records
     */
    List<ApplicationApprovedRecordDTO> findAllRecords();

    /**
     * Find application approved record with corresponding id.
     * @param id id of application approved record
     * @return return application approved record with certain id
     */
    ApplicationApprovedRecordDTO findRecordById(UUID id);

    /**
     * Retrieve all application approved records with corresponding car.
     * @param carId id of certain car
     * @return list of all application approved records with certain car
     */
    List<ApplicationApprovedRecordDTO> findAllRecordsByCar(UUID carId);

    /**
     * Retrieve all application approved records of certain user.
     * @param userId id of certain user
     * @return list of all application approved records of certain user
     */
    List<ApplicationApprovedRecordDTO> findAllRecordsByUser(UUID userId);

    /**
     * Retrieve all application approved records of certain user.
     * @param userEmail email of certain user
     * @return list of all application approved records of certain user
     */
    List<ApplicationApprovedRecordDTO> findAllRecordsByUserEmail(String userEmail);

    /**
     * Retrieve all application approved records created between certain dates.
     * @param from start date
     * @param to end date
     * @return list of all application approved records created between certain dates
     */
    List<ApplicationApprovedRecordDTO> findAllRecordsCreatedBetween(Date from, Date to);
}
