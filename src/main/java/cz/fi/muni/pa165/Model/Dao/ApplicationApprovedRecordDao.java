/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.ApplicationApprovedRecord;
import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public interface ApplicationApprovedRecordDao {

    public List<ApplicationApprovedRecord> findAll();
    public ApplicationApprovedRecord findById(UUID id);
    public List<ApplicationApprovedRecord> findByCar(Car c);
    public List<ApplicationApprovedRecord> findByUser(User u);
    public List<ApplicationApprovedRecord> getRecordsCreatedBetween(Date from, Date to);
    
    void create(ApplicationApprovedRecord r);
    void delete(ApplicationApprovedRecord r);
    
}
