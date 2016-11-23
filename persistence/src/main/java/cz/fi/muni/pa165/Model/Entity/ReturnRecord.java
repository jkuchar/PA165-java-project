/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.springframework.util.Assert;

import java.util.Date;

/**
 *
 * @author charlliz
 */
@Entity
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
    
    protected ReturnRecord() {}
    
    public ReturnRecord(Car car, User user, RentRecord rent, String comment, int fuel, int odometer, Date created) {
        super(car, user, comment, created);
        
        Assert.notNull(fuelState, "Cannot exist without fuel state.");
        this.fuelState = fuel;
        
        Assert.notNull(odometerState, "Cannot exist without odometer state.");
        this.odometerState = odometer;
 
        Assert.notNull(rent, "Cannot exist without rent record.");
        this.rentRecord = rent;
    }

    public ReturnRecord(Car car, User user, RentRecord rent, String comment, int fuel, int odometer) {
        this(car, user, rent, comment, fuel, odometer, new Date());
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReturnRecord)) {
            return false;
        }

        ReturnRecord rec = (ReturnRecord) o;

        return this.getId().equals(rec.getId());

    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
