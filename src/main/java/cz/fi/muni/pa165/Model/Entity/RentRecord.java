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
public class RentRecord extends CarAuditLogItem{

    @NotNull
    @Column(nullable = false)
    private int fuelState;
    
    @NotNull
    @Column(nullable = false)
    private int odometerState;

    @OneToOne
    private ApplicationApprovedRecord approvedRecord;
    
    public RentRecord(Car car, User user, ApplicationApprovedRecord approved, String comment, int fuel, int odometer) {
        super(car, user, comment);
        
        Assert.notNull(fuelState, "Cannot exist without fuel state.");
        this.fuelState = fuel;
        
        Assert.notNull(odometerState, "Cannot exist without odometer state.");
        this.odometerState = odometer;
        
        this.approvedRecord = approved;
    
    }    
    
    // DO NOT REMOVE! Hibernate hack:
    // @link http://stackoverflow.com/questions/2935826/why-does-hibernate-require-no-argument-constructor#comment9688725_2971717
        protected RentRecord() {
    }
        
        public int getFuelState() {
        return fuelState;
    }

    public void setFuelState(int fuelState) {
        this.fuelState = fuelState;
    }

    public int getOdometerState() {
        return odometerState;
    }

    public void setOdometerState(int odometerState) {
        this.odometerState = odometerState;
    }  

    public ApplicationApprovedRecord getApprovedRecord() {
        return approvedRecord;
    }

    public void setApprovedRecord(ApplicationApprovedRecord approvedRecord) {
        this.approvedRecord = approvedRecord;
    }
       
}
