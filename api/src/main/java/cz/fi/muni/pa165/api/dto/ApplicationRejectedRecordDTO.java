/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public class ApplicationRejectedRecordDTO{
    
    private UUID id;

    private CarDTO car;

    private UserDTO user;

    @NotNull
    private Date created;

    @NotNull
    @Size(min = 3)
    private String comment;
    
    private RentApplicationDTO rentApplication;

    public void setRentApplication(RentApplicationDTO app) {
        rentApplication = app;
    } 
    
    public RentApplicationDTO getRentApplication() {
        return rentApplication;
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

    public void setCar(CarDTO carDTO) {
        this.car = carDTO;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO userDTO) {
        this.user = userDTO;
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationRejectedRecordDTO that = (ApplicationRejectedRecordDTO) o;
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
