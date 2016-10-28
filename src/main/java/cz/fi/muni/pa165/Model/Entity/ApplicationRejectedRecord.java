package cz.fi.muni.pa165.Model.Entity;


import org.springframework.util.Assert;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

final public class ApplicationRejectedRecord extends CarAuditLogItem {

    @NotNull
    @OneToOne
    private RentApplication application;

    public ApplicationRejectedRecord(Car car, User user, Date created, String comment, RentApplication application) {
        super(car, user, created, comment);

        Assert.notNull(application, "Cannot exist without rent application record.");
        this.application = application;
    }

    public RentApplication getApplication() {
        return application;
    }
}
