package cz.fi.muni.pa165.service;


import cz.fi.muni.pa165.model.CarAuditLogItemType;
import cz.fi.muni.pa165.model.entity.CarAuditLogItem;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jkuchar
 */
public interface CarAuditLogItemService {
    
     /**
     * Method to retrieve all car audit log items from db.
     * @return all return car audit log items
     */  
    List<CarAuditLogItem> findAll();

    /**
     * Method to retrieve return car audit log item with corresponding id.
     * @param id id of car audit log item
     * @return car audit log item with certain id
     */    
    CarAuditLogItem findById(UUID id);

    /**
     * Method to retrieve car audit log items with corresponding car.
     * @param carId is id of certain car
     * @return list of all car audit log items with certain car
     */    
    List<CarAuditLogItem> findByCar(UUID carId);
    
    /**
     * Method to find all car audit log items of certain user.
     * @param userId is id of certain user
     * @return list of all car audit log items of certain user
     */    
    List<CarAuditLogItem> findByUser(UUID userId);

    /**
     * Method to retrieve car audit log items between certain dates.
     * @param from start date
     * @param to end date
     * @return list of all car audit log items which were created between certain dates
     */    
    List<CarAuditLogItem> getRecordsCreatedBetween(Date from, Date to);

    
    /**
     * Find the newest car audit log items
     * @return list of newest car audit log items.
     */    
    List<CarAuditLogItem> findAllFromNewest();

    /**
     * Returns state of the last non-closed branch of state tree
     * @param carId is id of car
     * @return actual log item type of certain car
     */
    CarAuditLogItemType findLogState(UUID carId);

    /**
     * Find last car audit log item of certain car
     * @param carId is id of car
     * @return last car audit log item of certain car
     */
    CarAuditLogItem findLastLogItem(UUID carId);
}
