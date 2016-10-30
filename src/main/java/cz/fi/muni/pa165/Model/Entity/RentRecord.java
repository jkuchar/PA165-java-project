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
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.util.Assert;

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
    
    
    // DO NOT REMOVE! Hibernate hack:
    // @link http://stackoverflow.com/questions/2935826/why-does-hibernate-require-no-argument-constructor#comment9688725_2971717
    protected RentRecord() {
    }
    
    public RentRecord(Car car, User user, ApplicationApprovedRecord approved, String comment, int fuel, int odometer) {
        super(car, user, comment);
        
        Assert.notNull(fuelState, "Cannot exist without fuel state.");
        this.fuelState = fuel;
        
        Assert.notNull(odometerState, "Cannot exist without odometer state.");
        this.odometerState = odometer;

        Assert.notNull(approvedRecord, "Cannot exist without application approved record.");
        this.approvedRecord = approved;
    
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
        if ((o == null) || !(o instanceof RentRecord)) {
            return false;
        }
        RentRecord other = (RentRecord) o;
        if (getFuelState() !=  other.getFuelState() ) {
            return false;
        }
        if (getOdometerState() != other.getOdometerState() ) {
            return false;
        }
        if (approvedRecord != null ? !approvedRecord.equals(other.getApprovedRecord()) : other.getApprovedRecord() != null) {
            return false;
        }
	return true;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getFuelState()).append(getOdometerState()).append(getApprovedRecord().getId()).toHashCode();
    }
}
