package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.CarAuditLogItemDTO;
import cz.fi.muni.pa165.api.facade.CarAuditLogItemFacade;
import cz.fi.muni.pa165.service.CarAuditLogItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jkuchar
 */
@Transactional
@Service
public class CarAuditLogItemFacadeImpl implements CarAuditLogItemFacade {


    private final BeanMappingService beanMappingService;

    private final CarAuditLogItemService carAuditLogItemService;

    @Autowired
    public CarAuditLogItemFacadeImpl(CarAuditLogItemService carAuditLogItemService, BeanMappingService beanMappingService) {
        this.carAuditLogItemService = carAuditLogItemService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public List<CarAuditLogItemDTO> findAll() {
        return beanMappingService.mapTo(carAuditLogItemService.findAll(), CarAuditLogItemDTO.class);
    }

    @Override
    public CarAuditLogItemDTO findById(UUID id) {
        return beanMappingService.mapTo(carAuditLogItemService.findById(id), CarAuditLogItemDTO.class);
    }

    @Override
    public List<CarAuditLogItemDTO> findByCar(UUID carId) {
        return beanMappingService.mapTo(carAuditLogItemService.findByCar(carId), CarAuditLogItemDTO.class);
    }

    @Override
    public List<CarAuditLogItemDTO> findByUser(UUID userId) {
        return beanMappingService.mapTo(carAuditLogItemService.findByUser(userId), CarAuditLogItemDTO.class);
    }

    @Override
    public List<CarAuditLogItemDTO> getRecordsCreatedBetween(Date from, Date to) {
        return beanMappingService.mapTo(carAuditLogItemService.getRecordsCreatedBetween(from, to), CarAuditLogItemDTO.class);
    }
}
