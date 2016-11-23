package cz.fi.muni.pa165.Api;

import cz.fi.muni.pa165.Api.DTO.CarAuditLogItemDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * This file is part of PA165 school project.
 */
public interface CarAuditLogItemFacade {

    List<CarAuditLogItemDTO> findAll();

    CarAuditLogItemDTO findById(UUID id);

    List<CarAuditLogItemDTO> findByCar(UUID carId);

    List<CarAuditLogItemDTO> findByUser(UUID userId);

    List<CarAuditLogItemDTO> getRecordsCreatedBetween(Date from, Date to);


}
