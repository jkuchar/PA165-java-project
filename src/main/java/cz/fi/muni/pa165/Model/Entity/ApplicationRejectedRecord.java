package cz.fi.muni.pa165.Model.Entity;


import org.springframework.util.Assert;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author rtrembecky
 */
@Entity
final public class ApplicationRejectedRecord extends CarAuditLogItem {

    @NotNull
    @OneToOne
    private RentApplication application;

    // DO NOT REMOVE! Hibernate hack:
    // @link http://stackoverflow.com/questions/2935826/why-does-hibernate-require-no-argument-constructor#comment9688725_2971717
        protected ApplicationRejectedRecord() {
    }
    
    public ApplicationRejectedRecord(Car car, User user, Date created, String comment, RentApplication application) {
        super(car, user, created, comment);

        Assert.notNull(application, "Cannot exist without rent application record.");
        this.application = application;
    }

    public RentApplication getApplication() {
        return application;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationRejectedRecord)) {
            return false;
        }

        ApplicationRejectedRecord rec = (ApplicationRejectedRecord) o;

        return this.getId().equals(rec.getId());

    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
