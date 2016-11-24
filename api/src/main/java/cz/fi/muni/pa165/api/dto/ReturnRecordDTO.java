package cz.fi.muni.pa165.api.dto;

import cz.fi.muni.pa165.model.entity.RentRecord;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created by SARMIR on 24. 11. 2016.
 */
public class ReturnRecordDTO {

    @NotNull
    public int fuelState;

    @NotNull
    public int odometerState;

    @NotNull
    public RentRecord rentRecord;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReturnRecordDTO that = (ReturnRecordDTO) o;
        return Objects.equals(fuelState, that.fuelState) &&
                Objects.equals(odometerState, that.odometerState) &&
                Objects.equals(rentRecord, that.rentRecord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelState, odometerState, rentRecord);
    }


}
