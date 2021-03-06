/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.dto;

import cz.fi.muni.pa165.enums.CarState;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public class CarDTO {

    private UUID id;

    @NotNull
    @Size(min = 1)
    private String serialNumber;

    @NotNull
    @Size(min = 1)
    private String regPlateNumber;

    @NotNull
    @Size(min = 1)
    private String manufacturer;

    @NotNull
    @Size(min = 1)
    private String type;

    @NotNull
    private int seats;

    private CarState state;

    @NotNull
    private Date establishDate;

    @NotNull
    private Date discardDate;


    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public void setDiscardDate(Date discardDate) {
        this.discardDate = discardDate;
    }

    public void setState(CarState state){
        this.state = state;
    }

    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;
    }

    public void setRegPlateNumber(String regPlateNumber){
        this.regPlateNumber = regPlateNumber;
    }

    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }

    public void setType(String type){
        this.type = type;
    }
    
    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public UUID getId() { return id; }
    
    public void setId(UUID id) { 
        this.id = id; 
    }

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

    public CarState getState() { return state; }

    public Date getEstablishDate() {
        return establishDate;
    }

    public Date getDiscardDate() {
        return discardDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO that = (CarDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(serialNumber, that.serialNumber) &&
                Objects.equals(regPlateNumber, that.regPlateNumber) &&
                Objects.equals(manufacturer, that.manufacturer) &&
                Objects.equals(type, that.type) &&
                Objects.equals(seats, that.seats) &&
                Objects.equals(state, that.state) &&
                Objects.equals(establishDate, that.establishDate) &&
                Objects.equals(discardDate, that.discardDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serialNumber, regPlateNumber, manufacturer, type, seats, state, establishDate, discardDate);
    } 
}
