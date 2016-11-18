package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.ApplicationRejectedRecord;
import cz.fi.muni.pa165.Model.Entity.User;
import cz.fi.muni.pa165.Model.Entity.Car;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author rtrembecky
 */
public interface ApplicationRejectedRecordDao {

    /**
     * Method retrieve all application rejected records from db
     * @return all application rejected records
     */
    public List<ApplicationRejectedRecord> findAll();

    /**
     * Method retrieve item with corresponding id
     * @param id id of application rejected record
     * @return return application rejected record with certain id
     */
    public ApplicationRejectedRecord findById(UUID id);

    /**
     * Method retrieve application rejected records with corresponding car.
     * @param c certain car
     * @return list of all application rejected records with certain car
     */
    public List<ApplicationRejectedRecord> findByCar(Car c);

    /**
     * Method to find all application rejected records of certain user.
     * @param u certain user
     * @return list of all application rejected records of certain user
     */
    public List<ApplicationRejectedRecord> findByUser(User u);

        /**
     * Method retrieve application rejected records betweens certain dates.
     * @param from start date
     * @param to end date
     * @return list of all application rejected records which were created between certain dates
     */
    public List<ApplicationRejectedRecord> getRecordsCreatedBetween(Date from, Date to);
    
    /**
     * Method insert new application rejected record into db.
     * @param r is new application rejected record
     */
    void create(ApplicationRejectedRecord r);

    /**
     * Method delete application rejected record from db.
     * @param r is application rejected record to delete
     */
    void delete(ApplicationRejectedRecord r);
}