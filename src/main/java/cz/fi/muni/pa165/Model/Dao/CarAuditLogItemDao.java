package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.User;
import cz.fi.muni.pa165.Model.Entity.CarAuditLogItem;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author rtrembecky
 */
public interface CarAuditLogItemDao {
    public List<CarAuditLogItem> findAll();
    public List<CarAuditLogItem> findById(UUID id);
    public List<CarAuditLogItem> findByCar(Car c);
    public List<CarAuditLogItem> findByUser(User u);
    public List<CarAuditLogItem> getRecordsCreatedBetween(Date from, Date to);

    void create(CarAuditLogItem r);
    void delete(CarAuditLogItem r);
}
