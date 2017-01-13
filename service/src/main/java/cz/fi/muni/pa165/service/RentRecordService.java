package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.entity.RentRecord;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public interface RentRecordService {

    /**
     * Method to retrieve all rent records from db.
     * @return all rent records
     */
    public List<RentRecord> findAll();

    /**
     * Method to retrieve rent record with corresponding id.
     * @param id id of rent record
     * @return return rent record with certain id
     */
    public RentRecord findById(UUID id);

    /**
     * Method to retrieve rent records with corresponding car.
     * @param carId is id of certain car
     * @return list of all rent records with certain car
     */
    public List<RentRecord> findByCar(UUID carId);

    /**
     * Method to find all rent records of certain user.
     * @param userId is id of certain user
     * @return list of all rent records of certain user
     */
    public List<RentRecord> findByUser(UUID userId);

    /**
     * Method to retrieve rent records between certain dates.
     * @param from start date
     * @param to end date
     * @return list of all rent records which were created between certain dates
     */
    public List<RentRecord> getRecordsCreatedBetween(Date from, Date to);

    /**
     * Create new rent record.
     * @param r is new rent record
     */
    void create(RentRecord r);


}
