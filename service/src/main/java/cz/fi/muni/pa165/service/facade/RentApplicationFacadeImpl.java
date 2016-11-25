package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.api.dto.RentApplicationDTO;
import cz.fi.muni.pa165.api.facade.RentApplicationFacade;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.RentApplication;
import cz.fi.muni.pa165.model.entity.User;
import cz.fi.muni.pa165.service.CarService;
import cz.fi.muni.pa165.service.RentApplicationService;
import cz.fi.muni.pa165.service.UserService;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *  @author jkuchar
 */
@Transactional
@Service
public class RentApplicationFacadeImpl implements RentApplicationFacade {

    private final BeanMappingService beanMappingService;

    private final RentApplicationService carAuditLogItemService;

    private final UserService userService;

    private final CarService carService;

    @Autowired
    public RentApplicationFacadeImpl(BeanMappingService beanMappingService,
                                     RentApplicationService carAuditLogItemService, UserService userService,
                                     CarService carService) {
        this.beanMappingService = beanMappingService;
        this.carAuditLogItemService = carAuditLogItemService;
        this.userService = userService;
        this.carService = carService;
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
        User user = userService.findById(r.getUser().getId());
        Car car = carService.findCarById(r.getCar().getId());
        RentApplication ra = new RentApplication(car, user, r.getComment(), r.getFrom(), r.getTo());
        carAuditLogItemService.create(ra);
        return ra.getId();
    }
}
