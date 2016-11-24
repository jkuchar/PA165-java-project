package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.Api.DTO.RentApplicationDTO;
import cz.fi.muni.pa165.Model.Entity.RentApplication;
import cz.fi.muni.pa165.Service.CarAuditLogItemService;
import cz.fi.muni.pa165.Service.RentApplicationService;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * This file is part of PA165 school project.
 */
@Transactional
@Service
public class RentApplicationFacadeImpl implements RentApplicationFacade {

    private final BeanMappingService beanMappingService;

    private final RentApplicationService carAuditLogItemService;

    @Autowired
    public RentApplicationFacadeImpl(BeanMappingService beanMappingService, RentApplicationService carAuditLogItemService) {
        this.beanMappingService = beanMappingService;
        this.carAuditLogItemService = carAuditLogItemService;
    }

    @Override
    public List<RentApplicationDTO> findAll() {
        return beanMappingService.mapTo(carAuditLogItemService.findAll(), RentApplicationDTO.class);
    }

    @Override
    public RentApplicationDTO findById(UUID id) {
        return beanMappingService.mapTo(carAuditLogItemService.findById(id), RentApplicationDTO.class);
    }

    @Override
    public List<RentApplicationDTO> findByCar(UUID carId) {
        return beanMappingService.mapTo(carAuditLogItemService.findByCar(carId), RentApplicationDTO.class);
    }

    @Override
    public List<RentApplicationDTO> findByUser(UUID userId) {
        return beanMappingService.mapTo(carAuditLogItemService.findByUser(userId), RentApplicationDTO.class);
    }

    @Override
    public List<RentApplicationDTO> getRecordsCreatedBetween(Date from, Date to) {
        return beanMappingService.mapTo(carAuditLogItemService.getRecordsCreatedBetween(from, to), RentApplicationDTO.class);
    }

    @Override
    public UUID create(RentApplicationDTO r) {
        // todo: retrieve car and user when services are available
//        RentApplication ra = new RentApplication(car, user, r.comment, r.from, r.to);
//        carAuditLogItemService.create(ra);
//        return ra.getId();
        throw new NotImplementedException();
    }

    @Override
    public void delete(UUID id) {
        carAuditLogItemService.delete(id);
    }
}
