package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.dao.ReturnRecordDao;
import cz.fi.muni.pa165.model.entity.ReturnRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public class ReturnRecordServiceImpl implements ReturnRecordService{

    private final ReturnRecordDao returnRecordDao;

    @Autowired
    public ReturnRecordServiceImpl(ReturnRecordDao returnRecordDao) {
        this.returnRecordDao = returnRecordDao;
    }


    @Override
    public List<ReturnRecord> findAll() {
        return returnRecordDao.findAll();
    }

    @Override
    public ReturnRecord findById(UUID id) {
        return returnRecordDao.findById(id);
    }

    @Override
    public List<ReturnRecord> findByCar(UUID carId) {
        return returnRecordDao.findByCar(carId);
    }

    @Override
    public List<ReturnRecord> findByUser(UUID userId) {
        return returnRecordDao.findByUser(userId);
    }

    @Override
    public List<ReturnRecord> getRecordsCreatedBetween(Date from, Date to) {
        return returnRecordDao.getRecordsCreatedBetween(from, to);
    }

    @Override
    public void create(ReturnRecord r) {
        returnRecordDao.create(r);
    }
}
