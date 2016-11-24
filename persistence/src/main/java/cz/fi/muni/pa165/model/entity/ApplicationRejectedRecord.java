package cz.fi.muni.pa165.model.entity;


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
public class ApplicationRejectedRecord extends CarAuditLogItem {

    @NotNull
    @OneToOne
    private RentApplication application;

    protected ApplicationRejectedRecord() {}
    
    public ApplicationRejectedRecord(Car car, User user, String comment, RentApplication application, Date created) {
        super(car, user, comment, created);

        Assert.notNull(application, "Cannot exist without rent application record.");
        this.application = application;
    }

    public ApplicationRejectedRecord(Car car, User user, String comment, RentApplication application) {
        this(car, user, comment, application, new Date());
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