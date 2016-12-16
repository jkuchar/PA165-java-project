package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.CarAuditLogItemDTO;
import cz.fi.muni.pa165.api.dto.CarLogPossibleStateDTO;
import cz.fi.muni.pa165.api.dto.CarLogStateDTO;
import cz.fi.muni.pa165.api.facade.CarAuditLogItemFacade;
import cz.fi.muni.pa165.model.CarAuditLogItemType;
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

    @Override
    public CarLogStateDTO findLogState(UUID carId) {
        CarAuditLogItem lastItem = carAuditLogItemService.findLastLogItem(carId);
        if(lastItem == null) return null;

        return mapStateTo(lastItem.getType(), lastItem.getId());
    }

    @NotNull
    private CarLogStateDTO mapStateTo(CarAuditLogItemType type, UUID id) {
        List<CarLogPossibleStateDTO> possibleNextStates = new ArrayList<>();
        for(CarAuditLogItemType possibleSuccessor : type.getPossibleSuccessors()) {
            possibleNextStates.add(
                    new CarLogPossibleStateDTO(possibleSuccessor.getName(), possibleSuccessor.getId())
            );
        }

        return new CarLogStateDTO(
                type.getName(),
                possibleNextStates,
                id
        );
    }


    public List<CarLogPossibleStateDTO> getInitialStates() {
        final List<CarAuditLogItemType> initialStates = CarAuditLogItemType.getInitialStates();
        if(initialStates.size() != 1) throw new RuntimeException("Currently no support for more initial states.");
        final CarAuditLogItemType initialState = initialStates.get(0);

        final List<CarLogPossibleStateDTO> carLogPossibleStateDTOS = new ArrayList<>();
        carLogPossibleStateDTOS.add(new CarLogPossibleStateDTO(initialState.getName(), initialState.getId()));
        return carLogPossibleStateDTOS;
    }
}
