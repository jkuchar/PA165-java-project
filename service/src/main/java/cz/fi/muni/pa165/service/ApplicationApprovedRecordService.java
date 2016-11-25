package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.entity.ApplicationApprovedRecord;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author rtrembecky
 */
public interface ApplicationApprovedRecordService {

    /**
     * Method to retrieve all application approved records from db.
     * @return all application approved records
     */
    List<ApplicationApprovedRecord> findAll();

    /**
     * Method to retrieve application approved record with corresponding id.
     * @param id id of application approved record
     * @return return application approved record with certain id
     */
    ApplicationApprovedRecord findById(UUID id);

    /**
     * Method to retrieve application approved records with corresponding car.
     * @param c certain car
     * @return list of all application approved records with certain car
     */
    List<ApplicationApprovedRecord> findByCar(Car c);

    /**
     * Method to find all application approved records of certain user.
     * @param u certain user
     * @return list of all application approved records of certain user
     */
    List<ApplicationApprovedRecord> findByUser(User u);

    /**
     * Method to retrieve application approved records between certain dates.
     * @param from start date
     * @param to end date
     * @return list of all application approved records which were created between certain dates
     */
    List<ApplicationApprovedRecord> getRecordsCreatedBetween(Date from, Date to);

    /**
     * Create new application approved record.
     * @param r is new application approved record
     */
    void create(ApplicationApprovedRecord r);

}
