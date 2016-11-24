package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.dao.RentRecordDao;
import cz.fi.muni.pa165.model.entity.RentRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public class RentRecordServiceImpl implements RentRecordService {

    private final RentRecordDao rentRecordDao;

    @Autowired
    public RentRecordServiceImpl(RentRecordDao rentRecordDao) {
        this.rentRecordDao = rentRecordDao;
    }

    @Override
    public List<RentRecord> findAll() {
        return rentRecordDao.findAll();
    }

    @Override
    public RentRecord findById(UUID id) {
        return rentRecordDao.findById(id);
    }

    @Override
    public List<RentRecord> findByCar(UUID carId) {
        return rentRecordDao.findByCar(carId);
    }

    @Override
    public List<RentRecord> findByUser(UUID userId) {
        return rentRecordDao.findByUser(userId);
    }

    @Override
    public List<RentRecord> getRecordsCreatedBetween(Date from, Date to) {
        return rentRecordDao.getRecordsCreatedBetween(from, to);
    }

    @Override
    public void create(RentRecord r) {

    }


}
