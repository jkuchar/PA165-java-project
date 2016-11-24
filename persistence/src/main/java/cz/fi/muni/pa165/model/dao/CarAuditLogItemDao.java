package cz.fi.muni.pa165.model.dao;

import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.CarAuditLogItem;
import cz.fi.muni.pa165.model.entity.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author rtrembecky
 */
public interface CarAuditLogItemDao {

    /**
     * Method to retrieve all car audit log items from db.
     * @return all car audit log items
     */
    public List<CarAuditLogItem> findAll();

    /**
     * Method to retrieve car audit log item with corresponding id.
     * @param id id of car audit log items
     * @return car audit log items with certain id
     */
    public CarAuditLogItem findById(UUID id);

    @Deprecated
    public List<CarAuditLogItem> findByCar(Car c);

    /**
     * Method to retrieve car audit log items with corresponding car.
     * @param id the car id
     * @return list of all car audit log items with certain car
     */
    public List<CarAuditLogItem> findByCar(UUID id);

    /**
     * Method to find all car audit log items of certain user.
     * @param id the user id
     * @return list of all car audit log items of certain user
     */
    public List<CarAuditLogItem> findByUser(UUID id);

    @Deprecated
    public List<CarAuditLogItem> findByUser(User u);

    /**
     * Method retrieve car audit log items between certain dates.
     * @param from start date
     * @param to end date
     * @return list of all car audit log items which were created between certain dates
     */
    public List<CarAuditLogItem> getRecordsCreatedBetween(Date from, Date to);
}
