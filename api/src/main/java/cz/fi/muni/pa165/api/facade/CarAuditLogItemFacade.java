package cz.fi.muni.pa165.api.facade;


import cz.fi.muni.pa165.api.dto.CarAuditLogItemDTO;
import cz.fi.muni.pa165.api.dto.CarLogStateDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *  @author jkuchar
 */
public interface CarAuditLogItemFacade {

    List<CarAuditLogItemDTO> findAll();

    List<CarAuditLogItemDTO> findAllFromNewest();

    CarAuditLogItemDTO findById(UUID id);

    List<CarAuditLogItemDTO> findByCar(UUID carId);

    List<CarAuditLogItemDTO> findByUser(UUID userId);

    List<CarAuditLogItemDTO> getRecordsCreatedBetween(Date from, Date to);

    CarLogStateDTO findLogState(UUID carId);

}
