package cz.fi.muni.pa165.model.entity;

import cz.fi.muni.pa165.model.enums.CarState;
import cz.fi.muni.pa165.model.DomainException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Created by SARMIR on 26. 10. 2016.
 * @author jakubsarmir
 */

@SuppressWarnings("WeakerAccess")
@Entity
public class Car {


    @Id
    @NotNull
    private UUID id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String serialNumber;

    @NotNull
    @Column(unique = true, nullable = false)
    private String regPlateNumber;

    @NotNull
    private String manufacturer;

    @NotNull
    private String type;

    @NotNull
    private int seats;

    @NotNull
    @Enumerated
    private CarState state;

    @DateTimeFormat
    @Temporal(TemporalType.TIMESTAMP)
    private Date establishDate;

    @DateTimeFormat
    @Temporal(TemporalType.TIMESTAMP)
    private Date discardDate;

    protected Car(){}

    public Car(String serialNumber, String regPlateNumber, String manufacturer, String type, int numberOfSeats, Date establishDate) {
        this.id = UUID.randomUUID();

        Assert.notNull(serialNumber, "Cannot create car with no serial number.");
        this.serialNumber = serialNumber;

        Assert.notNull(regPlateNumber, "Cannot create car with no registration plate number.");
        this.regPlateNumber = regPlateNumber;

        Assert.notNull(manufacturer, "Cannot create car with no manufacturer.");
        this.manufacturer = manufacturer;

        Assert.notNull(type, "Cannot create car with no type.");
        this.type = type;

        Assert.notNull(numberOfSeats, "Cannot create car without number of seats.");
        this.seats = numberOfSeats;

        this.state = CarState.OK;

        Assert.notNull(establishDate, "Cannot create car without estrablish date.");
        this.establishDate = establishDate;

    }

    public Car(String serialNumber, String regPlateNumber, String manufacturer, String type, int numberOfSeats) {
        this(serialNumber,regPlateNumber, manufacturer,  type, numberOfSeats, new Date());
    }

    public void changeState(CarState state) throws DomainException{
        if(state == this.state) {
            return;
        }
        Assert.notNull(state, "State is mandatory");

        if (!this.state.allowTransition(state)) {
            throw DomainException.carStateTransitionNotAllowed(this.state, state);
        }

        if (state == CarState.DISCARDED){
            this.discardDate = new Date();
        }
        this.state = state;
    }


    // use case: fix typos in Car decription
    public void setSerialNumber(String serialNumber){
        Assert.notNull(serialNumber, "Cannot create car with no serial number.");
        this.serialNumber = serialNumber;
    }

    public void setRegPlateNumber(String regPlateNumber){
        Assert.notNull(regPlateNumber, "Cannot create car with no registration plate number.");
        this.regPlateNumber = regPlateNumber;
    }

    public void setManufacturer(String manufacturer){
        Assert.notNull(manufacturer, "Cannot create car with no manufacturer.");
        this.manufacturer = manufacturer;
    }

    public void setType(String type){
        Assert.notNull(type, "Cannot create car with no type.");
        this.type = type;
    }

    public void setNumberOfSeats(int numberOfSeats){
        Assert.notNull(numberOfSeats, "Cannot create car without number of seats.");
        this.seats = numberOfSeats;
    }



    public UUID getId() { return id; }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getRegPlateNumber() {
        return regPlateNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getType() {
        return type;
    }

    public int getNumberOfSeats() { return seats; }

    public CarState getCarState() { return state; }

    public Date getEstablishDate() {
        return establishDate;
    }

    public Date getDiscardDate() {
        return discardDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        return id.equals(car.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
