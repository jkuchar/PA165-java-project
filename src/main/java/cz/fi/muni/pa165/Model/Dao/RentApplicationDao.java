/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.RentApplication;
import cz.fi.muni.pa165.Model.Entity.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public interface RentApplicationDao {
    
    public List<RentApplication> findAll();
    public RentApplication findById(UUID id);
    public List<RentApplication> findByCar(Car c);
    public List<RentApplication> findByUser(User u);
    public List<RentApplication> getRecordsCreatedBetween(Date from, Date to);
    
    void create(RentApplication r);
    void delete(RentApplication r);
    
}
