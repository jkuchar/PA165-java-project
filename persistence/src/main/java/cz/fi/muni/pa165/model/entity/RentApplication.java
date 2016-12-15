/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.model.entity;

import cz.fi.muni.pa165.model.CarAuditLogItemType;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author charlliz
 */
@Entity
public class RentApplication extends CarAuditLogItem{

    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date from;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date to;

    @Override
    public CarAuditLogItemType getType() {
        return CarAuditLogItemType.RENT_APPLICATION;
    }

    protected RentApplication() {}
    
    public RentApplication(Car car, User user, String comment, Date from, Date to, Date created) {
        super(car, user, comment, created);
        
        Assert.notNull(from, "Cannot exist without from date.");
        this.from = from;
        
        Assert.notNull(to, "Cannot exist without to date.");
        this.to = to;
    }

    public RentApplication(Car car, User user, String comment, Date from, Date to) {
        this(car, user, comment, from, to, new Date());
    }
    
    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RentApplication)) {
            return false;
        }

        RentApplication app = (RentApplication) o;

        return this.getId().equals(app.getId());

    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
