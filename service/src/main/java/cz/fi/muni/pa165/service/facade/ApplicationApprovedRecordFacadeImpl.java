package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.api.dto.ApplicationApprovedRecordDTO;
import cz.fi.muni.pa165.api.facade.ApplicationApprovedRecordFacade;
import cz.fi.muni.pa165.model.entity.ApplicationApprovedRecord;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.RentApplication;
import cz.fi.muni.pa165.model.entity.User;
import cz.fi.muni.pa165.service.ApplicationApprovedRecordService;
import cz.fi.muni.pa165.service.CarService;
import cz.fi.muni.pa165.service.RentApplicationService;
import cz.fi.muni.pa165.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author rtrembecky
 */
@Service
@Transactional
public class ApplicationApprovedRecordFacadeImpl implements ApplicationApprovedRecordFacade {

    private final BeanMappingService beanMappingService;

    private final ApplicationApprovedRecordService approvedRecordService;

    private final UserService userService;

    private final CarService carService;

    private final RentApplicationService rentApplicationService;

    @Autowired
    public ApplicationApprovedRecordFacadeImpl (BeanMappingService beanMappingService,
                                         ApplicationApprovedRecordService approvedRecordService,
                                         UserService userService, CarService carService,
                                         RentApplicationService rentApplicationService) {
        this.beanMappingService = beanMappingService;
        this.approvedRecordService = approvedRecordService;
        this.userService = userService;
        this.carService = carService;
        this.rentApplicationService = rentApplicationService;
    }

    @Override
    public UUID create(ApplicationApprovedRecordDTO r) {
        Car car = carService.findCarById(r.getCar().getId());
        User user = userService.findById(r.getUser().getId());
        RentApplication rentApplication = rentApplicationService.findById(r.getRentApplication().getId());
        ApplicationApprovedRecord app = new ApplicationApprovedRecord(car, user, r.getFrom(), r.getTo(),
                r.getComment(), rentApplication);
        return app.getId();
    }

    @Override
    public List<ApplicationApprovedRecordDTO> findAllRecords() {
        return beanMappingService.mapTo(approvedRecordService.findAll(), ApplicationApprovedRecordDTO.class);
    }

    @Override
    public ApplicationApprovedRecordDTO findRecordById(UUID id) {
        return beanMappingService.mapTo(approvedRecordService.findById(id), ApplicationApprovedRecordDTO.class);
    }

    @Override
    public List<ApplicationApprovedRecordDTO> findAllRecordsByCar(UUID carId) {
        Car car = carService.findCarById(carId);
        return beanMappingService.mapTo(approvedRecordService.findByCar(car), ApplicationApprovedRecordDTO.class);
    }

    @Override
    public List<ApplicationApprovedRecordDTO> findAllRecordsByUser(UUID userId) {
        User user = userService.findById(userId);
        return beanMappingService.mapTo(approvedRecordService.findByUser(user), ApplicationApprovedRecordDTO.class);
    }

    @Override
    public List<ApplicationApprovedRecordDTO> findAllRecordsCreatedBetween(Date from, Date to) {
        return beanMappingService.mapTo(approvedRecordService.getRecordsCreatedBetween(from, to),
                ApplicationApprovedRecordDTO.class);
    }
}
