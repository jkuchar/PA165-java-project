package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.ReturnRecordDTO;
import cz.fi.muni.pa165.api.facade.ReturnRecordFacade;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.RentRecord;
import cz.fi.muni.pa165.model.entity.ReturnRecord;
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
@Service
@Transactional
public class ReturnRecordFacadeImpl implements ReturnRecordFacade{

    private final BeanMappingService beanMappingService;

    private final ReturnRecordService returnRecordService;

    private final UserService userService;

    private final CarService carService;

    private final RentRecordService rentRecordService;

    @Autowired
    public ReturnRecordFacadeImpl(BeanMappingService beanMappingService, ReturnRecordService returnRecordService, UserService userService, CarService carService, RentRecordService rentRecordService) {
        this.beanMappingService = beanMappingService;
        this.returnRecordService = returnRecordService;
        this.userService = userService;
        this.carService = carService;
        this.rentRecordService = rentRecordService;
    }


    @Override
    public List<ReturnRecordDTO> findAll() {
        return beanMappingService.mapTo(returnRecordService.findAll(), ReturnRecordDTO.class);
    }

    @Override
    public ReturnRecordDTO findById(UUID id) {
        return beanMappingService.mapTo(returnRecordService.findById(id), ReturnRecordDTO.class);
    }

    @Override
    public List<ReturnRecordDTO> findByCar(UUID carId) {
        return beanMappingService.mapTo(returnRecordService.findByCar(carId), ReturnRecordDTO.class);
    }

    @Override
    public List<ReturnRecordDTO> findByUser(UUID userId) {
        return beanMappingService.mapTo(returnRecordService.findByUser(userId), ReturnRecordDTO.class);
    }

    @Override
    public List<ReturnRecordDTO> getRecordsCreatedBetween(Date from, Date to) {
        return beanMappingService.mapTo(returnRecordService.getRecordsCreatedBetween(from, to), ReturnRecordDTO.class);
    }

    @Override
    public UUID create(ReturnRecordDTO r) {
        Car car = carService.findCarById(r.getCar().getId());
        User user = userService.findById(r.getUser().getId());
        RentRecord rentRecord = rentRecordService.findById(r.getRentRecord().getId());
        ReturnRecord rr = new ReturnRecord(car, user, rentRecord, r.getComment(), r.getFuelState(), r.getOdometerState(), r.getCreated());
        returnRecordService.create(rr);
        return rr.getId();
    }
}
