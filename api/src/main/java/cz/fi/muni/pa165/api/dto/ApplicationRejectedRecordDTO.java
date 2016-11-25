/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.dto;

import java.util.UUID;

/**
 *
 * @author charlliz
 */
public class ApplicationRejectedRecordDTO extends CarAuditLogItemDTO {

    private UUID rentApplicationId;

    public UUID getRentApplicationId() {
        return rentApplicationId;
    }

    public void setRentApplicationId(UUID rentApplicationId) {
        this.rentApplicationId = rentApplicationId;
    }

    //todo: equals and hashcode methods
}
