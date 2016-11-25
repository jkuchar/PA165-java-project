package cz.fi.muni.pa165.api.dto;


import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public class RentRecordDTO {

    @NotNull
    public int fuelState;

    @NotNull
    public int odometerState;

    @NotNull
    public UUID approvedRecordId;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentRecordDTO that = (RentRecordDTO) o;
        return Objects.equals(fuelState, that.fuelState) &&
                Objects.equals(odometerState, that.odometerState) &&
                Objects.equals(approvedRecordId, that.approvedRecordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelState, odometerState, approvedRecordId);
    }

}
