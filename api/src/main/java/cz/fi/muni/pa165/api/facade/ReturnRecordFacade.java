package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.ReturnRecordDTO;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public interface ReturnRecordFacade {

    /**
     * Method to retrieve all return records from db.
     * @return all return records
     */
    public List<ReturnRecordDTO> findAll();

    /**
     * Method to retrieve return record with corresponding id.
     * @param id id of return record
     * @return return return record with certain id
     */
    public ReturnRecordDTO findById(UUID id);

    /**
     * Method to retrieve return records with corresponding car.
     * @param c certain car
     * @return list of all return records with certain car
     */
    public List<ReturnRecordDTO> findByCar(UUID carId);


    /**
     * Method to find all return records of certain user.
     * @param u certain user
     * @return list of all return records of certain user
     */
    public List<ReturnRecordDTO> findByUser(UUID userId);

    /**
     * Method to retrieve return records between certain dates.
     * @param from start date
     * @param to end date
     * @return list of all return records which were created between certain dates
     */
    public List<ReturnRecordDTO> getRecordsCreatedBetween(Date from, Date to);

    /**
     * Method to insert new return record into db.
     * @param r is new return record
     */
    void create(ReturnRecordDTO r);

    /**
     * Method delete return record from db.
     * @param r is return record to delete
     */


}
