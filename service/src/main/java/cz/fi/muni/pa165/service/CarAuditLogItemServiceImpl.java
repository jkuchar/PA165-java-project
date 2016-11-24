package cz.fi.muni.pa165.Service;

import cz.fi.muni.pa165.Model.Dao.CarAuditLogItemDao;
import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.CarAuditLogItem;
import cz.fi.muni.pa165.Model.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jkuchar
 */
@Service
public class CarAuditLogItemServiceImpl implements CarAuditLogItemService {

    private final CarAuditLogItemDao dao;

    @Autowired
    public CarAuditLogItemServiceImpl(CarAuditLogItemDao dao) {
        this.dao = dao;
    }

    @Override
    public List<cz.fi.muni.pa165.Model.Entity.CarAuditLogItem> findAll() {
        return dao.findAll();
    }

    @Override
    public List<CarAuditLogItem> findByCar(UUID carId) {
        return dao.findByCar(carId);
    }

    @Override
    public List<CarAuditLogItem> findByUser(UUID userId) {
        return dao.findByCar(userId);
    }

    @Override
    public cz.fi.muni.pa165.Model.Entity.CarAuditLogItem findById(UUID id) {
        return dao.findById(id);
    }

    @Override
    public List<cz.fi.muni.pa165.Model.Entity.CarAuditLogItem> getRecordsCreatedBetween(Date from, Date to) {
        return dao.getRecordsCreatedBetween(from, to);
    }
}
