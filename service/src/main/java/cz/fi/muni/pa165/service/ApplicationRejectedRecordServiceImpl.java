/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.dao.ApplicationRejectedRecordDao;
import cz.fi.muni.pa165.model.entity.ApplicationRejectedRecord;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author charlliz
 */
public class ApplicationRejectedRecordServiceImpl implements ApplicationRejectedRecordService{

    @Autowired
    private ApplicationRejectedRecordDao recordDao;
        
    @Override
    public void create(ApplicationRejectedRecord r) {
        recordDao.create(r);
    }

    @Override
    public List<ApplicationRejectedRecord> findAllRecords() {
        return recordDao.findAll();
    }

    @Override
    public ApplicationRejectedRecord findRecordById(UUID id) {
        return recordDao.findById(id);
    }

    @Override
    public List<ApplicationRejectedRecord> getAllRecordsByCar(Car c) {
        return recordDao.findByCar(c);
    }

    @Override
    public List<ApplicationRejectedRecord> getAllRecordsByUser(User u) {
        return recordDao.findByUser(u);
    }

    @Override
    public List<ApplicationRejectedRecord> getAllRecordsCreatedBetween(Date from, Date to) {
        return recordDao.getRecordsCreatedBetween(from, to);
    }   
}
