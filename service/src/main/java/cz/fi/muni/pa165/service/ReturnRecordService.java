package cz.fi.muni.pa165.service;


import cz.fi.muni.pa165.model.entity.ReturnRecord;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public interface ReturnRecordService {

    public List<ReturnRecord> findAll();


    public ReturnRecord findById(UUID id);


    public List<ReturnRecord> findByCar(UUID carId);


    public List<ReturnRecord> findByUser(UUID userId);


    public List<ReturnRecord> getRecordsCreatedBetween(Date from, Date to);


    void create(ReturnRecord r);

}
