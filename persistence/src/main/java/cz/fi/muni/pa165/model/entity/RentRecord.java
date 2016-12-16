/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.model.entity;

import cz.fi.muni.pa165.model.CarAuditLogItemType;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author charlliz
 */
@Entity
public class RentRecord extends CarAuditLogItem{

    @NotNull
    @Column(nullable = false)
    private int fuelState;
    
    @NotNull
    @Column(nullable = false)
    private int odometerState;

    @NotNull
    @OneToOne
    private ApplicationApprovedRecord approvedRecord;

    @Override
    public CarAuditLogItemType getType() {
        return CarAuditLogItemType.RENT_RECORD;
    }

    protected RentRecord() {}

    @Override
    public RentApplication getRentApplication() {
        return approvedRecord.getRentApplication();
    }

    public RentRecord(Car car, User user, ApplicationApprovedRecord approved, String comment, int fuel, int odometer, Date created) {
        super(car, user, comment, created);
        
        Assert.notNull(fuelState, "Cannot exist without fuel state.");
        this.fuelState = fuel;
        
        Assert.notNull(odometerState, "Cannot exist without odometer state.");
        this.odometerState = odometer;

        Assert.notNull(approved, "Cannot exist without application approved record.");
        this.approvedRecord = approved;
    
    }

    public RentRecord(Car car, User user, ApplicationApprovedRecord approved, String comment, int fuel, int odometer) {
        this(car, user, approved, comment, fuel, odometer, new Date());
    }
    
    public RentRecord(String comment, ApplicationApprovedRecord approved, int fuel, int odometer) {
        this(approved.getCar(), approved.getUser(), approved, comment, fuel, odometer, new Date());
    }

    public int getFuelState() {
        return fuelState;
    }

    public int getOdometerState() {
        return odometerState;
    }    

    public ApplicationApprovedRecord getApprovedRecord() {
        return approvedRecord;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RentRecord)) {
            return false;
        }

        RentRecord rec = (RentRecord) o;

        return this.getId().equals(rec.getId());

    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
