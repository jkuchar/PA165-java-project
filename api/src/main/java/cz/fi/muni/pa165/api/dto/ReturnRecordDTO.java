package cz.fi.muni.pa165.api.dto;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public class ReturnRecordDTO {

    private UUID id;

    private CarDTO car;

    private UserDTO user;

    private Date created;

    private String comment;

    private int fuelState;

    private int odometerState;

    private RentRecordDTO rentRecordDto;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO carDto) {
        this.car = carDto;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO userDto) {
        this.user = userDto;
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

    public RentRecordDTO getRentRecord() {
        return rentRecordDto;
    }

    public void setRentRecord(RentRecordDTO rentRecordDto) {
        this.rentRecordDto = rentRecordDto;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReturnRecordDTO that = (ReturnRecordDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(car, that.car) &&
                Objects.equals(user, that.user) &&
                Objects.equals(created, that.created) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(fuelState, that.fuelState) &&
                Objects.equals(odometerState, that.odometerState) &&
                Objects.equals(rentRecordDto, that.rentRecordDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, user, created, comment, fuelState, odometerState, rentRecordDto);
    }
}
