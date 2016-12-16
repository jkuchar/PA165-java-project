package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.CarAuditLogItemType;
import cz.fi.muni.pa165.model.dao.CarAuditLogItemDao;
import cz.fi.muni.pa165.model.entity.CarAuditLogItem;
import cz.fi.muni.pa165.model.entity.RentApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<CarAuditLogItem> findByCarChronologically(UUID carId) {
        return dao.findByCarChronologically(carId);
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

    @Override
    public List<CarAuditLogItem> findAllFromNewest() {
        return dao.findAllFromNewest();
    }

    @Override
    public CarAuditLogItemType findLogState(UUID carId) {
        final CarAuditLogItem lastLogItem = findLastLogItem(carId);
        if(lastLogItem == null) return null;

        return lastLogItem.getType();
    }

    public CarAuditLogItem findLastLogItem(UUID carId) {
        List<CarAuditLogItem> logItems = findByCarChronologically(carId);

        final NavigableMap<RentApplication, List<CarAuditLogItem>> rentApplicationMap = buildStateTreeForCar(logItems);

        // go from the end and find first state tree branch which is not in the end state; that is current state
        for(RentApplication rentApplication : rentApplicationMap.descendingKeySet()) {
            final List<CarAuditLogItem> carAuditLogItems = rentApplicationMap.get(rentApplication);
            CarAuditLogItem theLast = carAuditLogItems.get(carAuditLogItems.size() - 1);

            if(!theLast.getType().isEndState()) {
                return theLast;
            }
        }

        // if there is all closed or there are no records at all, car is in initial state
        return null;
    }

    /**
     * Builds projection of state type into list of record types
     *
     * TODO: move me to more proper place?
     */
    private NavigableMap<RentApplication, List<CarAuditLogItem>> buildStateTreeForCar(List<CarAuditLogItem> logItems) {
        // construct records state tree
        NavigableMap<RentApplication, List<CarAuditLogItem>> carRecords = new TreeMap<>(); // todo: navigaeble vs comprarable

        for(CarAuditLogItem logItem : logItems) {
            List<CarAuditLogItem> logItemsList = carRecords.get(logItem.getRentApplication());

            if(logItemsList == null) {
                logItemsList = new ArrayList<>();
            }

            logItemsList.add(logItem);
            carRecords.put(logItem.getRentApplication(), logItemsList);
        }
        return carRecords;
    }
}
