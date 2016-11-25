package cz.fi.muni.pa165.api.dto;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public class RentRecordDTO {

    private UUID id;

    private CarDTO carDto;

    private UserDTO userDto;

    private Date created;

    private String comment;

    private int fuelState;

    private int odometerState;

    private ApplicationApprovedRecordDTO approvedRecordDto;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CarDTO getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDTO carDto) {
        this.carDto = carDto;
    }

    public UserDTO getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
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

    public ApplicationApprovedRecordDTO getApprovedRecordDto() {
        return approvedRecordDto;
    }

    public void setApprovedRecordDto(ApplicationApprovedRecordDTO approvedRecordDto) {
        this.approvedRecordDto = approvedRecordDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentRecordDTO that = (RentRecordDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(carDto, that.carDto) &&
                Objects.equals(userDto, that.userDto) &&
                Objects.equals(created, that.created) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(fuelState, that.fuelState) &&
                Objects.equals(odometerState, that.odometerState) &&
                Objects.equals(approvedRecordDto, that.approvedRecordDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carDto, userDto, created, comment, fuelState, odometerState, approvedRecordDto);
    }
}
