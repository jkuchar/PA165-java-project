package cz.fi.muni.pa165.Api.DTO;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author jkuchar
 */
public class CarAuditLogItemDTO {

    @NotNull
    public UUID id;

    @NotNull
    public UUID carId;

    @NotNull
    public UUID userId;

    @NotNull
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
