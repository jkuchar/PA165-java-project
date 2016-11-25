package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.dao.ApplicationApprovedRecordDao;
import cz.fi.muni.pa165.model.entity.ApplicationApprovedRecord;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author rtrembecky
 */
@Service
public class ApplicationApprovedRecordServiceImpl implements ApplicationApprovedRecordService {

    private ApplicationApprovedRecordDao dao;

    @Autowired
    public ApplicationApprovedRecordServiceImpl (ApplicationApprovedRecordDao dao) {
        this.dao = dao;
    }

    @Override
    public List<ApplicationApprovedRecord> findAll() {
        return dao.findAll();
    }

    @Override
    public ApplicationApprovedRecord findById(UUID id) {
        return dao.findById(id);
    }

    @Override
    public List<ApplicationApprovedRecord> findByCar(Car c) {
        return dao.findByCar(c);
    }

    @Override
    public List<ApplicationApprovedRecord> findByUser(User u) {
        return dao.findByUser(u);
    }

    @Override
    public List<ApplicationApprovedRecord> getRecordsCreatedBetween(Date from, Date to) {
        return dao.getRecordsCreatedBetween(from, to);
    }

    @Override
    public void create(ApplicationApprovedRecord r) {
        dao.create(r);
    }
}
