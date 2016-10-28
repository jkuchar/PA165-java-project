/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Entity;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.util.Assert;

/**
 *
 * @author charlliz
 */
public class RentApplication extends CarAuditLogItem{

    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date from;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date to;
    
    public RentApplication(Car car, User user, String comment, Date from, Date to) {
        super(car, user, comment);
        
        Assert.notNull(from, "Cannot exist without from date.");
        this.from = from;
        
        Assert.notNull(to, "Cannot exist without to date.");
        this.to = to;
    }
    
    // DO NOT REMOVE! Hibernate hack:
    // @link http://stackoverflow.com/questions/2935826/why-does-hibernate-require-no-argument-constructor#comment9688725_2971717
        protected RentApplication() {
    }
    
    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }
}
