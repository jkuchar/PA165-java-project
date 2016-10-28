package cz.fi.muni.pa165.Model.Entity;

import cz.fi.muni.pa165.Model.CarState;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Created by SARMIR on 26. 10. 2016.
 */

@SuppressWarnings("WeakerAccess")
@Entity
public class Car {


    /* DO NOT REMOVE! hack explanation @link http://stackoverflow.com/questions/2935826/why-does-hibernate-require-no-argument-constructor#comment9688725_2971717 */
    protected Car(){}



    public Car(UUID id, int serialNumber, int regPlateNumber, String manufacturer, String type, int numberOfSeats, CarState carState, Date establishDate, Date discardDate) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.regPlateNumber = regPlateNumber;
        this.manufacturer = manufacturer;
        this.type = type;
        this.numberOfSeats = numberOfSeats;
        this.state = carState;
        this.establishDate = establishDate;
        this.discardDate = discardDate;
    }

    @Id
    private UUID id;

    @NotNull
    private int serialNumber;

    @NotNull
    private int regPlateNumber;

    @NotNull
    private String manufacturer;

    @NotNull
    private String type;

    @NotNull
    private int numberOfSeats;

    @NotNull
    private CarState state;

    private Date establishDate;

    private Date discardDate;



    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setRegPlateNumber(int regPlateNumber) {
        this.regPlateNumber = regPlateNumber;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setState(CarState state) {
        this.state = state;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public void setDiscardDate(Date discardDate) {
        this.discardDate = discardDate;
    }



    public UUID getID() { return id; }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getRegPlateNumber() {
        return regPlateNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getType() {
        return type;
    }

    public int getNumberOfSeats() { return numberOfSeats; }

    public CarState getCarState() { return state; }

    public Date getEstablishDate() {
        return establishDate;
    }

    public Date getDiscardDate() {
        return discardDate;
    }
}
