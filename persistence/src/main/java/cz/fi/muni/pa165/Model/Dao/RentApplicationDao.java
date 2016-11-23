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
    
    /**
     * Method to retrieve all rent applications from db.
     * @return all rent applications
     */
    public List<RentApplication> findAll();

    /**
     * Method to retrieve rent application with corresponding id.
     * @param id id of rent application
     * @return return rent application with certain id
     */
    public RentApplication findById(UUID id);

    /**
     * Method to retrieve rent applications with corresponding car.
     * @param c certain car
     * @return list of all rent applications with certain car
     */
    public List<RentApplication> findByCar(Car c);

    /**
     * Method to find all rent applications of certain user.
     * @param u certain user
     * @return list of all rent applications of certain user
     */
    public List<RentApplication> findByUser(User u);

    /**
     * Method to retrieve rent applications between certain dates.
     * @param from start date
     * @param to end date
     * @return list of all rent applications which were created between certain dates
     */
    public List<RentApplication> getRecordsCreatedBetween(Date from, Date to);
    
    /**
     * Method to insert new rent application into db.
     * @param r is new rent application
     */
    void create(RentApplication r);

    /**
     * Method delete rent application from db.
     * @param r is rent application to delete
     */
    void delete(RentApplication r);
    
}
