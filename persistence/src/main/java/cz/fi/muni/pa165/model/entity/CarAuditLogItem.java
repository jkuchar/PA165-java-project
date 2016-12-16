package cz.fi.muni.pa165.model.entity;

import cz.fi.muni.pa165.model.CarAuditLogItemType;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author rtrembecky
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CarAuditLogItem implements java.lang.Comparable {

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

    public abstract CarAuditLogItemType getType();

    public CarAuditLogItem(Car car, User user, String comment, Date created) {
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
        this(car, user, comment, new Date());
    }

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


    /**
     * Returns base rent application which this object refers to
     */
    public abstract RentApplication getRentApplication();


    @Override
    public int compareTo(Object o) {
        // some order needed to get TreeMap working properly
        // Order needs to be stable not really meaningful

        if(o == null) throw new RuntimeException("Cannot compare to null");

        if(!(o instanceof CarAuditLogItem)) {
            throw new RuntimeException("Cannot compare to different type then CarAuditLogItem");
        }

        return getId().compareTo(
                ((CarAuditLogItem) o).getId()
        );
    }
}
