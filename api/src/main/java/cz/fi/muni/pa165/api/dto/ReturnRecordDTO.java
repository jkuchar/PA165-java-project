package cz.fi.muni.pa165.api.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public class ReturnRecordDTO {
    
    @NotNull
    private UUID id;

    @NotNull
    private CarDTO car;

    @NotNull
    private UserDTO user;

    @NotNull
    private Date created;

    private String comment;
    
    @NotNull
    private int fuelState;

    @NotNull
    private int odometerState;

    @NotNull
    private RentRecordDTO rentRecord;

    public int getFuelState() {
        return fuelState;
    }

    public void setFuelState(int fuelState) {
        this.fuelState = fuelState;
    }

    public int getOdometerState() {
        return odometerState;
    }

    public void setOdometerState(int odometerState) {
        this.odometerState = odometerState;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public RentRecordDTO getRentRecord() {
        return rentRecord;
    }

    public void setRentRecord(RentRecordDTO rentRecord) {
        this.rentRecord = rentRecord;
    }

    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReturnRecordDTO that = (ReturnRecordDTO) o;
        return  Objects.equals(id, that.id) &&
                Objects.equals(car, that.car) &&
                Objects.equals(user, that.user) &&
                Objects.equals(created, that.created) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(fuelState, that.fuelState) &&
                Objects.equals(odometerState, that.odometerState) &&
                Objects.equals(rentRecord, that.rentRecord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, user, created, comment, fuelState, odometerState, rentRecord);
    }
}
