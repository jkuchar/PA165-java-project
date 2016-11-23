package cz.fi.muni.pa165.Api.DTO;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author jkuchar
 */
public class RentApplicationDTO {

    @NotNull
    public UUID id;

    @NotNull
    public UUID carId;

    @NotNull
    public UUID userId;

    @NotNull
    public Date created;

    public String comment;

    public Date from;

    public Date to;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentApplicationDTO that = (RentApplicationDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(carId, that.carId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(created, that.created) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(from, that.from) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carId, userId, created, comment, from, to);
    }
}
