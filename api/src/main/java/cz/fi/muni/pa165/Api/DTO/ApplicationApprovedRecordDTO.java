package cz.fi.muni.pa165.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author rtrembecky
 */
public class ApplicationApprovedRecordDTO {
    
    private UUID id;

    private CarDTO car;

    private UserDTO user;

    @NotNull
    private Date created;

    @NotNull
    @Size(min = 3)
    private String comment;

    @NotNull
    private Date from;

    @NotNull
    private Date to;

    private RentApplicationDTO rentApplication;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public RentApplicationDTO getRentApplication() {
        return rentApplication;
    }

    public void setRentApplication(RentApplicationDTO rentApplication) {
        this.rentApplication = rentApplication;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationApprovedRecordDTO that = (ApplicationApprovedRecordDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(car, that.car) &&
                Objects.equals(user, that.user) &&
                Objects.equals(created, that.created) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(rentApplication, that.rentApplication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, user, created, comment, rentApplication);
    } 
}
