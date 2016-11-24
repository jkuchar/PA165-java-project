package cz.fi.muni.pa165.api.dto;

import java.util.Date;
import java.util.UUID;

/**
 * @author rtrembecky
 */
public class ApplicationApprovedRecordDTO {
    private UUID id;

    private UUID carId;

    private UUID userId;

    private Date created;

    private String comment;

    private Date from;

    private Date to;

    private RentApplicationDTO rentApplicationDTO;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public RentApplicationDTO getRentApplicationDTO() {
        return rentApplicationDTO;
    }

    public void setRentApplicationDTO(RentApplicationDTO rentApplicationDTO) {
        this.rentApplicationDTO = rentApplicationDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationApprovedRecordDTO)) {
            return false;
        }

        ApplicationApprovedRecordDTO app = (ApplicationApprovedRecordDTO) o;

        return id.equals(app.getId());

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
