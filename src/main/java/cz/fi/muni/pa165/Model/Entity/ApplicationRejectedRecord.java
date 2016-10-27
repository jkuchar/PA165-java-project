package cz.fi.muni.pa165.Model.Entity;


import java.util.Date;
import java.util.UUID;

public class ApplicationRejectedRecord extends CarAuditLogItem {
    public ApplicationRejectedRecord(Car car, User user, Date created, String comment) {
        super(car, user, created, comment);
    }
}
