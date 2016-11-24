/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.Model.Entity.ApplicationRejectedRecord;
import cz.fi.muni.pa165.Model.Entity.*;
import cz.fi.muni.pa165.dto.ApplicationRejectedRecordDTO;
import cz.fi.muni.pa165.service.ApplicationRejectedRecordService;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.CarService;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author charlliz
 */
@Service
@Transactional
public class ApplicationRejectedRecordFacadeImpl implements ApplicationRejectedRecordFacade{

    @Autowired
    private ApplicationRejectedRecordService recordService;

    @Autowired
    private CarService carService;
    /*
    @Autowired
    private UserService userService;*/
        
    @Autowired
    private BeanMappingService beanMappingService;
        
    @Override
    public UUID create(ApplicationRejectedRecordDTO r) {
        /*
        User user = userService.findUserById(r.getUserId());
        Car car = carService.findCarById(r.getCarId());
        ApplicationRejectedRecord record = new ApplicationRejectedRecord(car, user,r.getComment(),r.getApplication(),r.getCreated());
        recordService.create(record);
        return  record.getId();*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ApplicationRejectedRecordDTO> findAllRecords() {
        return beanMappingService.mapTo(recordService.findAllRecords(),ApplicationRejectedRecordDTO.class);
    }

    @Override
    public ApplicationRejectedRecordDTO findRecordById(UUID id) {
        return beanMappingService.mapTo(recordService.findRecordById(id),ApplicationRejectedRecordDTO.class);
    }

    @Override
    public List<ApplicationRejectedRecordDTO> getAllRecordsByCar(UUID carId) {
       	Car car = carService.findCarById(carId);
	List<ApplicationRejectedRecord> records = recordService.getAllRecordsByCar(car);

	return beanMappingService.mapTo(records, ApplicationRejectedRecordDTO.class); 
    }

    @Override
    public List<ApplicationRejectedRecordDTO> getAllRecordsByUser(UUID userId) {
        /*
       	User user = userService.findUserById(userId);
	List<ApplicationRejectedRecord> records = recordService.getAllRecordsByUser(user);

	return beanMappingService.mapTo(records, ApplicationRejectedRecordDTO.class); 
                */
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ApplicationRejectedRecordDTO> getAllRecordsCreatedBetween(Date from, Date to) {
        return beanMappingService.mapTo(recordService.getAllRecordsCreatedBetween(from, to),ApplicationRejectedRecordDTO.class);
    }
    
}
