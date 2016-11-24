package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.dao.CarAuditLogItemDao;
import cz.fi.muni.pa165.model.dao.CarAuditLogItemDaoImpl;
import cz.fi.muni.pa165.model.entity.CarAuditLogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<CarAuditLogItem> findAll() {
        return dao.findAll();
    }

    @Override
    public List<CarAuditLogItem> findByCar(UUID carId) {
        return dao.findByCar(carId);
    }

    @Override
    public List<CarAuditLogItem> findByUser(UUID userId) {
        return dao.findByUser(userId);
    }

    @Override
    public CarAuditLogItem findById(UUID id) {
        return dao.findById(id);
    }

    @Override
    public List<CarAuditLogItem> getRecordsCreatedBetween(Date from, Date to) {
        return dao.getRecordsCreatedBetween(from, to);
    }
}
