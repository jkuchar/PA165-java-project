package cz.fi.muni.pa165.api.dto;

import cz.fi.muni.pa165.model.entity.ApplicationApprovedRecord;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.RentRecord;
import cz.fi.muni.pa165.model.entity.User;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * @author jakubsarmir
 */
public class RentRecordDTO {

    @NotNull
    public int fuelState;

    @NotNull
    public int odometerState;

    @NotNull
    public ApplicationApprovedRecord approvedRecord;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentRecordDTO that = (RentRecordDTO) o;
        return Objects.equals(fuelState, that.fuelState) &&
                Objects.equals(odometerState, that.odometerState) &&
                Objects.equals(approvedRecord, that.approvedRecord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelState, odometerState, approvedRecord);
    }

}
