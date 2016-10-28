/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Entity;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.springframework.util.Assert;

/**
 *
 * @author charlliz
 */
public class ReturnRecord extends CarAuditLogItem{
    
    @NotNull
    @Column(nullable = false)
    private int fuelState;
    
    @NotNull
    @Column(nullable = false)
    private int odometerState;
    
    @NotNull
    @OneToOne
    private RentRecord rentRecord;
    
    public ReturnRecord(Car car, User user, RentRecord rent, String comment, int fuel, int odometer) {
        super(car, user, comment);
        
        Assert.notNull(fuelState, "Cannot exist without fuel state.");
        this.fuelState = fuel;
        
        Assert.notNull(odometerState, "Cannot exist without odometer state.");
        this.odometerState = odometer;
 
        Assert.notNull(rentRecord, "Cannot exist without rent record.");        
        this.rentRecord = rent;
             
    }    
    
    // DO NOT REMOVE! Hibernate hack:
    // @link http://stackoverflow.com/questions/2935826/why-does-hibernate-require-no-argument-constructor#comment9688725_2971717
        protected ReturnRecord() {
    }  

    public int getFuelState() {
        return fuelState;
    }

    public int getOdometerState() {
        return odometerState;
    }

    public RentRecord getRentRecord() {
        return rentRecord;
    }

}
