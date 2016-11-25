package cz.fi.muni.pa165.api.dto;

import cz.fi.muni.pa165.model.entity.RentRecord;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public class ReturnRecordDTO {

    @NotNull
    public int fuelState;

    @NotNull
    public int odometerState;

    @NotNull
    public UUID rentRecordId;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReturnRecordDTO that = (ReturnRecordDTO) o;
        return Objects.equals(fuelState, that.fuelState) &&
                Objects.equals(odometerState, that.odometerState) &&
                Objects.equals(rentRecordId, that.rentRecordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelState, odometerState, rentRecordId);
    }


}
