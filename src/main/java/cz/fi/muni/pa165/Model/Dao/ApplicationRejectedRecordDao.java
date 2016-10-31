package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.ApplicationRejectedRecord;
import cz.fi.muni.pa165.Model.Entity.User;
import cz.fi.muni.pa165.Model.Entity.Car;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author rtrembecky
 */
public interface ApplicationRejectedRecordDao {
    public List<ApplicationRejectedRecord> findAll();
    public ApplicationRejectedRecord findById(UUID id);
    public List<ApplicationRejectedRecord> findByCar(Car c);
    public List<ApplicationRejectedRecord> findByUser(User u);

    void create(ApplicationRejectedRecord r);
    void delete(ApplicationRejectedRecord r);
}