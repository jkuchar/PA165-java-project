package cz.fi.muni.pa165.api.facade;


import cz.fi.muni.pa165.api.dto.CarAuditLogItemDTO;
import cz.fi.muni.pa165.api.dto.CarLogPossibleStateDTO;
import cz.fi.muni.pa165.api.dto.CarLogStateDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *  @author jkuchar
 */
public interface CarAuditLogItemFacade {

    /**
     * Find all car audit log items.
     * @return list of all car audit log items
     */
    List<CarAuditLogItemDTO> findAll();

    /**
     * Find the newest car audit log items
     * @return list of newest car audit log items.
     */
    List<CarAuditLogItemDTO> findAllFromNewest();

    /**
     * Find certain car audit log item by id
     * @param id is id of car audit log item
     * @return car audit log item with certain id
     */
    CarAuditLogItemDTO findById(UUID id);

    /**
     * Find all car audit log items of certain car
     * @param carId is id of car
     * @return list of all car audit log items of certain car
     */
    List<CarAuditLogItemDTO> findByCar(UUID carId);

    /**
     * Find all car audit log items of certan user
     * @param userId is id of user
     * @return list of all car audit log items of certan user
     */
    List<CarAuditLogItemDTO> findByUser(UUID userId);

    /**
     * Find all car audit log items created between certain dates
     * @param from starting date
     * @param to end date
     * @return list of all car audit log items created between certain dates
     */
    List<CarAuditLogItemDTO> getRecordsCreatedBetween(Date from, Date to);

    /**
     * Find last car audit log item of certain car
     * @param carId is id of car
     * @return last car audit log item of certain car
     */
    CarLogStateDTO findLogState(UUID carId);

    /**
     * Return all possible initial states of car audit log item e.g. RENT_APPLICATION
     * @return list of all possibilities for initial state of car audit log item
     */
    List<CarLogPossibleStateDTO> getInitialStates();


    /**
     * Return true or false if recordTypeId is valid string identifier of CarAuditLogItemType
     * @param recordTypeId is string name of CarAuditLogItemType
     * @return true or false
     */
    boolean isRecordTypeValid(String recordTypeId);
}
