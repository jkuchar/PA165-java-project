package cz.fi.muni.pa165.Model.Entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Id;

public abstract class CarAuditLogItem {

    @Id
    protected UUID id;

    protected Car car;

    protected User user;

    protected Date created;

    protected String comment;

    protected CarAuditLogItem(Car car, User user, Date created, String comment) {
        this.id = UUID.randomUUID();
        this.car = car;
        this.user = user;
        this.created = created;
        this.comment = comment;
    }

    public CarAuditLogItem(Car car, User user, String comment) {
        this(car, user, new Date(), comment);
    }

    /* DO NOT REMOVE! hack explanation @link http://stackoverflow.com/questions/2935826/why-does-hibernate-require-no-argument-constructor#comment9688725_2971717 */
    protected CarAuditLogItem() {}

    public UUID getId() {
        return id;
    }
    public UUID getCarId() {
        return carId;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
