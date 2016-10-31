package cz.fi.muni.pa165.Model.Entity;

import org.springframework.util.Assert;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author rtrembecky
 */
@Entity
public abstract class CarAuditLogItem {

    @Id
    @NotNull
    private UUID id;

    @NotNull
    @ManyToOne
    private Car car;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    private Date created;

    private String comment;

    CarAuditLogItem(Car car, User user, Date created, String comment) {
        this.id = UUID.randomUUID();

        Assert.notNull(car, "Cannot create LogItem without car.");
        this.car = car;

        Assert.notNull(user, "Cannot create LogItem without user.");
        this.user = user;

        Assert.notNull(created, "Cannot create LogItem without creation date.");
        this.created = created;

        this.comment = comment;
    }

    CarAuditLogItem(Car car, User user, String comment) {
        this(car, user, new Date(), comment);
    }

    // DO NOT REMOVE! Hibernate hack:
    // @link http://stackoverflow.com/questions/2935826/why-does-hibernate-require-no-argument-constructor#comment9688725_2971717
    protected CarAuditLogItem() {}

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UUID getId() {
        return id;
    }
    public Car getCar() {
        return car;
    }
    public User getUser() {
        return user;
    }
    public Date getCreated() {
        return created;
    }
    public String getComment() {
        return comment;
    }
}
