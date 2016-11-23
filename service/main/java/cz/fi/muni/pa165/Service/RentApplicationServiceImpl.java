package cz.fi.muni.pa165.Service;

import cz.fi.muni.pa165.Model.Dao.RentApplicationDao;
import cz.fi.muni.pa165.Model.Entity.RentApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RentApplicationServiceImpl implements RentApplicationService {

    @Override
    public List<RentApplication> findAll() {
        return rentApplicationDao.findAll();
    }

    @Override
    public RentApplication findById(UUID id) {
        return rentApplicationDao.findById(id);
    }

    @Override
    public List<RentApplication> findByCar(UUID carId) {
        return rentApplicationDao.findByCar(carId);
    }

    @Override
    public List<RentApplication> findByUser(UUID userId) {
        return rentApplicationDao.findByUser(userId);
    }

    @Override
    public List<RentApplication> getRecordsCreatedBetween(Date from, Date to) {
        return rentApplicationDao.getRecordsCreatedBetween(from, to);
    }

    @Override
    public void create(RentApplication r) {
        rentApplicationDao.create(r);
    }

    @Override
    public void delete(RentApplication r) {
        rentApplicationDao.delete(r);
    }

    @Override
    public void delete(UUID id) {
        delete(findById(id));
    }

    private final RentApplicationDao rentApplicationDao;


    @Autowired
    public RentApplicationServiceImpl(RentApplicationDao rentApplicationDao) {
        this.rentApplicationDao = rentApplicationDao;
    }




}
