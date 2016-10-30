/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.RentRecord;
import cz.fi.muni.pa165.Model.Entity.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public interface RentRecordDao {

    public List<RentRecord> findAll();
    public RentRecord findById(UUID id);
    public List<RentRecord> findByCar(Car c);
    public List<RentRecord> findByUser(User u);
    public List<RentRecord> getRecordsCreatedBetween(Date from, Date to);
    
    void create(RentRecord r);
    void delete(RentRecord r);
    
}
