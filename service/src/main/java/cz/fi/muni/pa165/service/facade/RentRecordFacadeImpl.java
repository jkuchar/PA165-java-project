package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.RentRecordDTO;
import cz.fi.muni.pa165.api.facade.RentRecordFacade;
import cz.fi.muni.pa165.model.entity.ApplicationApprovedRecord;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.RentRecord;
import cz.fi.muni.pa165.model.entity.User;
import cz.fi.muni.pa165.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
@Transactional
@Service
public class RentRecordFacadeImpl implements RentRecordFacade {

    private final BeanMappingService beanMappingService;

    private final RentRecordService rentRecordService;

    private final UserService userService;

    private final CarService carService;

    private final ApplicationApprovedRecordService approvedRecordService;

    @Autowired
    public RentRecordFacadeImpl(BeanMappingService beanMappingService, RentRecordService rentRecordService, UserService userService, CarService carService, ApplicationApprovedRecordService approvedRecordService) {
        this.beanMappingService = beanMappingService;
        this.rentRecordService = rentRecordService;
        this.userService = userService;
        this.carService = carService;
        this.approvedRecordService = approvedRecordService;
    }

    @Override
    public List<RentRecordDTO> findAll() {
        return beanMappingService.mapTo(rentRecordService.findAll(), RentRecordDTO.class);
    }

    @Override
    public RentRecordDTO findById(UUID id) {
        return beanMappingService.mapTo(rentRecordService.findById(id), RentRecordDTO.class);
    }

    @Override
    public List<RentRecordDTO> findByCar(UUID carId) {
        return beanMappingService.mapTo(rentRecordService.findByCar(carId), RentRecordDTO.class);
    }

    @Override
    public List<RentRecordDTO> findByUser(UUID userId) {
        return beanMappingService.mapTo(rentRecordService.findByUser(userId), RentRecordDTO.class);
    }

    @Override
    public List<RentRecordDTO> findByUserEmail(String userEmail) {
        User user = userService.findByEmail(userEmail);
        return beanMappingService.mapTo(rentRecordService.findByUser(user.getId()), RentRecordDTO.class);
    }

    @Override
    public List<RentRecordDTO> getRecordsCreatedBetween(Date from, Date to) {
        return beanMappingService.mapTo(rentRecordService.getRecordsCreatedBetween(from, to), RentRecordDTO.class);
    }

    @Override
    public UUID create(RentRecordDTO r) {
        Car car = carService.findCarById(r.getCar().getId());
        User user = userService.findById(r.getUser().getId());
        ApplicationApprovedRecord approvedRecord = approvedRecordService.findById(r.getApprovedRecord().getId());
        RentRecord rr = new RentRecord(car, user, approvedRecord, r.getComment(), r.getFuelState(), r.getOdometerState(), r.getCreated());
        rentRecordService.create(rr);
        return rr.getId();
    }
}
