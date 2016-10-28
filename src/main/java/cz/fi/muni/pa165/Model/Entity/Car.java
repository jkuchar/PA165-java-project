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



    public Car(String serialNumber, String regPlateNumber, String manufacturer, String type, int numberOfSeats, Date establishDate) {
        this.id = UUID.randomUUID();
        this.serialNumber = serialNumber;
        this.regPlateNumber = regPlateNumber;
        this.manufacturer = manufacturer;
        this.type = type;
        this.seats = numberOfSeats;
        this.state = CarState.OK;
        this.establishDate = establishDate;
    }

    public Car(String serialNumber, String regPlateNumber, String manufacturer, String type, int numberOfSeats) {
        this(serialNumber,regPlateNumber, manufacturer,  type, numberOfSeats, new Date());
    }

    @Id
    private UUID id;

    @NotNull
    private String serialNumber;

    @NotNull
    private String regPlateNumber;

    @NotNull
    private String manufacturer;

    @NotNull
    private String type;

    @NotNull
    private int seats;

    @NotNull
    private CarState state;

    private Date establishDate;

    private Date discardDate;


    public void changeState(CarState state) {
//sdfsdf
        if(this.state == CarState.OK){

            if (state == CarState.OK){
                System.out.println("Car is already in state OK");
            }
            else if (state == CarState.SERVICING) {
                this.state = state;
            }
            else if (state == CarState.DISCARDED){
                this.state = state;
                this.discardDate = new Date();
            }
        }

        else if (this.state == CarState.SERVICING){

            if (state == CarState.SERVICING){
                System.out.println("Car is already in state SERVICING");
            }
            else  if (state == CarState.OK){
                this.state = state;
            }
            else if (state == CarState.DISCARDED){
                this.state = state;
                this.discardDate = new Date();
            }
        }

        else if (this.state == CarState.DISCARDED){

            if (state == CarState.OK || state == CarState.SERVICING || state == CarState.DISCARDED){
                System.out.println("Car has already been DISCARDED");
            }

        }

    }


    // use case: fix typos in Car decription
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setRegPlateNumber(String regPlateNumber) {
        this.regPlateNumber = regPlateNumber;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.seats = numberOfSeats;
    }



    public UUID getID() { return id; }

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
