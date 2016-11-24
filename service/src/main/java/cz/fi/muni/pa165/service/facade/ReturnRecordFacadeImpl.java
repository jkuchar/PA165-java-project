package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.ReturnRecordDTO;
import cz.fi.muni.pa165.api.facade.ReturnRecordFacade;
import cz.fi.muni.pa165.service.ReturnRecordService;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jakubsarmir
 */
public class ReturnRecordFacadeImpl implements ReturnRecordFacade{

    private final BeanMappingService beanMappingService;

    private final ReturnRecordService returnRecordService;

    @Autowired
    public ReturnRecordFacadeImpl(BeanMappingService beanMappingService, ReturnRecordService returnRecordService) {
        this.beanMappingService = beanMappingService;
        this.returnRecordService = returnRecordService;
    }


    @Override
    public List<ReturnRecordDTO> findAll() {
        return beanMappingService.mapTo(returnRecordService.findAll(), ReturnRecordDTO.class);
    }

    @Override
    public ReturnRecordDTO findById(UUID id) {
        return beanMappingService.mapTo(returnRecordService.findById(id), ReturnRecordDTO.class);
    }

    @Override
    public List<ReturnRecordDTO> findByCar(UUID carId) {
        return beanMappingService.mapTo(returnRecordService.findByCar(carId), ReturnRecordDTO.class);
    }

    @Override
    public List<ReturnRecordDTO> findByUser(UUID userId) {
        return beanMappingService.mapTo(returnRecordService.findByUser(userId), ReturnRecordDTO.class);
    }

    @Override
    public List<ReturnRecordDTO> getRecordsCreatedBetween(Date from, Date to) {
        return beanMappingService.mapTo(returnRecordService.getRecordsCreatedBetween(from, to), ReturnRecordDTO.class);
    }

    @Override
    public void create(ReturnRecordDTO r) {
// todo: retrieve car and user when services are available
//        RentRecord rr = new RentApplication(car, user, r.comment, r.from, r.to);
//        carAuditLogItemService.create(ra);
//        return ra.getId();
        throw new NotImplementedException();
    }
}
