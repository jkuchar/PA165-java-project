/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.model.entity;

import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author charlliz
 */
@Entity
public class ApplicationApprovedRecord extends CarAuditLogItem{
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date from;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date to;
    
    @OneToOne
    @NotNull
    private RentApplication application;
    
    protected ApplicationApprovedRecord() {}
    
    public ApplicationApprovedRecord(Car car, User user, Date from, Date to, String comment, RentApplication app, Date created) {
        super(car, user, comment, created);
        
        Assert.notNull(from, "Cannot exist without from date.");
        this.from = from;
        
        Assert.notNull(to, "Cannot exist without to date.");
        this.to = to;
        
        Assert.notNull(app, "Cannot exist without rent application record.");
        this.application = app;
    }

    public ApplicationApprovedRecord(Car car, User user, Date from, Date to, String comment, RentApplication app) {
        this(car, user, from, to, comment, app, new Date());
    }
    
    public Date getFrom() {
        return from;
    }
    
    public Date getTo() {
        return to;
    }
    
    public RentApplication getApplication() {
        return application;
    } 
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationApprovedRecord)) {
            return false;
        }

        ApplicationApprovedRecord rec = (ApplicationApprovedRecord) o;

        return this.getId().equals(rec.getId());

    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
