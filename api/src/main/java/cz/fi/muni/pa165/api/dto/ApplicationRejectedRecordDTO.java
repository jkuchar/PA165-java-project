/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.dto;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author charlliz
 */
public class ApplicationRejectedRecordDTO{
    
    private UUID id;

    private CarDTO carDTO;

    private UserDTO userDTO;

    private Date created;

    private String comment;
    
    private RentApplicationDTO rentApplicationDTO;

    public void setRentApplicationDTO(RentApplicationDTO app) {
        rentApplicationDTO = app;
    } 
    
    public RentApplicationDTO getRentApplicationDTO() {
        return rentApplicationDTO;
    }  

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CarDTO getCarDTO() {
        return carDTO;
    }

    public void setCarDTO(CarDTO carDTO) {
        this.carDTO = carDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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
                Objects.equals(carDTO, that.carDTO) &&
                Objects.equals(userDTO, that.userDTO) &&
                Objects.equals(created, that.created) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(rentApplicationDTO, that.rentApplicationDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carDTO, userDTO, created, comment, rentApplicationDTO);
    }
}
