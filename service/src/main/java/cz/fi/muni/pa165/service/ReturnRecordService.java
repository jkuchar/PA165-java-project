package cz.fi.muni.pa165.service;


import cz.fi.muni.pa165.model.entity.ReturnRecord;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public interface ReturnRecordService {

    /**
     * Method to retrieve all return records from db.
     * @return all return records
     */    
    public List<ReturnRecord> findAll();

    /**
     * Method to retrieve return record with corresponding id.
     * @param id id of return record
     * @return return record with certain id
     */
    public ReturnRecord findById(UUID id);

    /**
     * Method to retrieve return records with corresponding car.
     * @param carId is id of certain car
     * @return list of all return records with certain car
     */
    public List<ReturnRecord> findByCar(UUID carId);

    /**
     * Method to find all return records of certain user.
     * @param userId is id of certain user
     * @return list of all return records of certain user
     */
    public List<ReturnRecord> findByUser(UUID userId);

    /**
     * Method to retrieve return records between certain dates.
     * @param from start date
     * @param to end date
     * @return list of all return records which were created between certain dates
     */
    public List<ReturnRecord> getRecordsCreatedBetween(Date from, Date to);

    /**
     * Create new return record.
     * @param r is new return record
     */
    void create(ReturnRecord r);

}
