package cz.fi.muni.pa165.api.dto;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author jkuchar
 */
public class CarAuditLogItemDTO {

    public UUID id;

    public UUID carId;

    public UUID userId;

    public Date created;

    public String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarAuditLogItemDTO that = (CarAuditLogItemDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(carId, that.carId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(created, that.created) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carId, userId, created, comment);
    }
}