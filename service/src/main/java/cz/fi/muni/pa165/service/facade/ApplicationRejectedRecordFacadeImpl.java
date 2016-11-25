/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.ApplicationRejectedRecordDTO;
import cz.fi.muni.pa165.api.facade.ApplicationRejectedRecordFacade;
import cz.fi.muni.pa165.model.entity.ApplicationRejectedRecord;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.RentApplication;
import cz.fi.muni.pa165.model.entity.User;
import cz.fi.muni.pa165.service.ApplicationRejectedRecordService;
import cz.fi.muni.pa165.service.CarService;
import cz.fi.muni.pa165.service.RentApplicationService;
import cz.fi.muni.pa165.service.UserService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
@Service
@Transactional
public class ApplicationRejectedRecordFacadeImpl implements ApplicationRejectedRecordFacade {

    private ApplicationRejectedRecordService recordService;

    private CarService carService;

    private UserService userService;

    private BeanMappingService beanMappingService;

    private RentApplicationService rentApplicationService;

    public ApplicationRejectedRecordFacadeImpl (ApplicationRejectedRecordService recordService, CarService carService,
                                                UserService userService, BeanMappingService beanMappingService,
                                                RentApplicationService rentApplicationService) {
        this.recordService = recordService;
        this.carService = carService;
        this.userService = userService;
        this.beanMappingService = beanMappingService;
        this.rentApplicationService = rentApplicationService;
    }
        
    @Override
    public UUID create(ApplicationRejectedRecordDTO r) {
        User user = userService.findById(r.userId);
        Car car = carService.findCarById(r.carId);
        RentApplication rentApplication = rentApplicationService.findById(r.getRentApplicationId());
        ApplicationRejectedRecord record = new ApplicationRejectedRecord(car, user, r.comment, rentApplication, r.created);
        recordService.create(record);
        return record.getId();
    }

    @Override
    public List<ApplicationRejectedRecordDTO> findAllRecords() {
        return beanMappingService.mapTo(recordService.findAllRecords(), ApplicationRejectedRecordDTO.class);
    }

    @Override
    public ApplicationRejectedRecordDTO findRecordById(UUID id) {
        return beanMappingService.mapTo(recordService.findRecordById(id), ApplicationRejectedRecordDTO.class);
    }

    @Override
    public List<ApplicationRejectedRecordDTO> getAllRecordsByCar(UUID carId) {
       	Car car = carService.findCarById(carId);
	    List<ApplicationRejectedRecord> records = recordService.getAllRecordsByCar(car);

	    return beanMappingService.mapTo(records, ApplicationRejectedRecordDTO.class);
    }

    @Override
    public List<ApplicationRejectedRecordDTO> getAllRecordsByUser(UUID userId) {
       	User user = userService.findById(userId);
	    List<ApplicationRejectedRecord> records = recordService.getAllRecordsByUser(user);

	    return beanMappingService.mapTo(records, ApplicationRejectedRecordDTO.class);
    }

    @Override
    public List<ApplicationRejectedRecordDTO> getAllRecordsCreatedBetween(Date from, Date to) {
        return beanMappingService.mapTo(recordService.getAllRecordsCreatedBetween(from, to), ApplicationRejectedRecordDTO.class);
    }
    
}
