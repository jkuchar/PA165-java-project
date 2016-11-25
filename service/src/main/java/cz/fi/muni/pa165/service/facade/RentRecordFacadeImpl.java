package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.api.dto.RentRecordDTO;
import cz.fi.muni.pa165.api.facade.RentRecordFacade;
import cz.fi.muni.pa165.service.RentRecordService;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
@Transactional
@Service
public class RentRecordFacadeImpl implements RentRecordFacade {

    private final BeanMappingService beanMappingService;

    private final RentRecordService rentRecordService;

    @Autowired
    public RentRecordFacadeImpl(BeanMappingService beanMappingService, RentRecordService rentRecordService) {
        this.beanMappingService = beanMappingService;
        this.rentRecordService = rentRecordService;
    }


    @Override
    public List<RentRecordDTO> findAll() {
        return beanMappingService.mapTo(rentRecordService.findAll(), RentRecordDTO.class);
    }

    @Override
    public RentRecordDTO findById(UUID id) {
        return beanMappingService.mapTo(rentRecordService.findById(id), RentRecordDTO.class);
    }

    @Override
    public List<RentRecordDTO> findByCar(UUID carId) {
        return beanMappingService.mapTo(rentRecordService.findByCar(carId), RentRecordDTO.class);
    }

    @Override
    public List<RentRecordDTO> findByUser(UUID userId) {
        return beanMappingService.mapTo(rentRecordService.findByUser(userId), RentRecordDTO.class);
    }

    @Override
    public List<RentRecordDTO> getRecordsCreatedBetween(Date from, Date to) {
        return beanMappingService.mapTo(rentRecordService.getRecordsCreatedBetween(from, to), RentRecordDTO.class);
    }

    @Override
    public UUID create(RentRecordDTO r) {
        // todo: retrieve car and user when services are available
//        RentRecord rr = new RentApplication(car, user, r.comment, r.from, r.to);
//        carAuditLogItemService.create(ra);
//        return ra.getId();
        throw new NotImplementedException();
    }

}
