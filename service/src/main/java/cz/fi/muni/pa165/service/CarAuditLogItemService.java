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
    List<CarAuditLogItem> findAll();

    CarAuditLogItem findById(UUID id);

    List<CarAuditLogItem> findByCar(UUID carId);
    List<CarAuditLogItem> findByUser(UUID userId);

    List<CarAuditLogItem> getRecordsCreatedBetween(Date from, Date to);

    List<CarAuditLogItem> findAllFromNewest();

    /**
     * Returns state of the last non-closed branch of state tree
     */
    CarAuditLogItemType getLogState(UUID carId);
}
