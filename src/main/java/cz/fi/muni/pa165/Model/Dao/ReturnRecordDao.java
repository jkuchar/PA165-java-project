/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.ReturnRecord;
import cz.fi.muni.pa165.Model.Entity.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public interface ReturnRecordDao {

    public List<ReturnRecord> findAll();
    public ReturnRecord findById(UUID id);
    public List<ReturnRecord> findByCar(Car c);
    public List<ReturnRecord> findByUser(User u);
    public List<ReturnRecord> getRecordsCreatedBetween(Date from, Date to);
    
    void create(ReturnRecord r);
    void delete(ReturnRecord r);
    
}
