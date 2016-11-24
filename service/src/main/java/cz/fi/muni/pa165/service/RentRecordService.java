package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.entity.RentRecord;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public interface RentRecordService {


    public List<RentRecord> findAll();


    public RentRecord findById(UUID id);


    public List<RentRecord> findByCar(UUID carId);


    public List<RentRecord> findByUser(UUID userId);


    public List<RentRecord> getRecordsCreatedBetween(Date from, Date to);


    void create(RentRecord r);


}
