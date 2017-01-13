/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.dto;

import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author charlliz
 */
public class CarCreateDTO {

    @NotNull
    private String serialNumber;

    @NotNull
    private String regPlateNumber;

    @NotNull
    @Size(min = 3, max = 30)
    private String manufacturer;
    
    private String type;

    @NotNull
    @Min(2)
    private int seats;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarCreateDTO that = (CarCreateDTO) o;
        return  Objects.equals(serialNumber, that.serialNumber) &&
                Objects.equals(regPlateNumber, that.regPlateNumber) &&
                Objects.equals(manufacturer, that.manufacturer) &&
                Objects.equals(type, that.type) &&
                Objects.equals(seats, that.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, regPlateNumber, manufacturer, type, seats);
    }     
}
