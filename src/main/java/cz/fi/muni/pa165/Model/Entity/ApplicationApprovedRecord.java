/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.util.Assert;

/**
 *
 * @author charlliz
 */
public class ApplicationApprovedRecord extends CarAuditLogItem{
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date from;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date to;
    
    @OneToOne
    private RentApplication application;
    
    public ApplicationApprovedRecord(Car car, User user, Date from, Date to, String comment, RentApplication app) {
        super(car, user, comment);
        
        Assert.notNull(from, "Cannot exist without from date.");
        this.from = from;
        
        Assert.notNull(to, "Cannot exist without to date.");
        this.to = to;
        
        Assert.notNull(application, "Cannot exist without rent application record.");        
        this.application = app;
    }
    
    // DO NOT REMOVE! Hibernate hack:
    // @link http://stackoverflow.com/questions/2935826/why-does-hibernate-require-no-argument-constructor#comment9688725_2971717
        protected ApplicationApprovedRecord() {
    }
    
    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
    
    public RentApplication getApplication() {
        return application;
    }

    public void setApplication(RentApplication application) {
        this.application = application;
    }
    
}
