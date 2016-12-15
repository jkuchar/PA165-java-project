package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.CarAuditLogItemDTO;
import cz.fi.muni.pa165.api.facade.CarAuditLogItemFacade;
import cz.fi.muni.pa165.model.entity.CarAuditLogItem;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.CarAuditLogItemService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @NotNull
    private List<CarAuditLogItemDTO> mapTo(List<CarAuditLogItem> in) {

        List<CarAuditLogItemDTO> dtos = new ArrayList<>();
        for(CarAuditLogItem item : in) {
            dtos.add(mapTo(item));
        }
        return dtos;
    }

    @NotNull
    private CarAuditLogItemDTO mapTo(CarAuditLogItem item) {
        CarAuditLogItemDTO dto = beanMappingService.mapTo(item, CarAuditLogItemDTO.class);

        // custom manual mapping here:
        if(item.getType() != null) { // this is needed because tests are using mocks which returns null; otherwise this is impossible
            dto.setType(item.getType().getName());
        }

        return dto;
    }

    @Autowired
    public CarAuditLogItemFacadeImpl(CarAuditLogItemService carAuditLogItemService, BeanMappingService beanMappingService) {
        this.carAuditLogItemService = carAuditLogItemService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public List<CarAuditLogItemDTO> findAll() {
        return mapTo(carAuditLogItemService.findAll());
    }

    @Override
    public List<CarAuditLogItemDTO> findAllFromNewest() {
        return mapTo(carAuditLogItemService.findAllFromNewest());
    }

    @Override
    public CarAuditLogItemDTO findById(UUID id) {
        return mapTo(carAuditLogItemService.findById(id));
    }

    @Override
    public List<CarAuditLogItemDTO> findByCar(UUID carId) {
        return mapTo(carAuditLogItemService.findByCar(carId));
    }

    @Override
    public List<CarAuditLogItemDTO> findByUser(UUID userId) {
        return mapTo(carAuditLogItemService.findByUser(userId));
    }

    @Override
    public List<CarAuditLogItemDTO> getRecordsCreatedBetween(Date from, Date to) {
        return mapTo(carAuditLogItemService.getRecordsCreatedBetween(from, to));
    }
}
