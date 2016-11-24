package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.RentRecord;
import cz.fi.muni.pa165.model.entity.User;
import cz.fi.muni.pa165.service.RentApplicationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public class RentRecordFacadeImpl implements  RentRecordFacade{

    private final BeanMappingService beanMappingService;

    private final RentRecordService rentRecordService;

    @Autowired
    public RentRecordFacadeImpl(BeanMappingService beanMappingService, RentRecordService rentRecordService) {
        this.beanMappingService = beanMappingService;
        this.rentRecordService = rentRecordService;
    }


    @Override
    public List<RentRecord> findAll() {
        return null;
    }

    @Override
    public RentRecord findById(UUID id) {
        return null;
    }

    @Override
    public List<RentRecord> findByCar(Car c) {
        return null;
    }

    @Override
    public List<RentRecord> findByUser(User u) {
        return null;
    }

    @Override
    public List<RentRecord> getRecordsCreatedBetween(Date from, Date to) {
        return null;
    }

    @Override
    public void create(RentRecord r) {

    }

    @Override
    public void delete(RentRecord r) {

    }
}
