package cz.fi.muni.pa165.model.dao;

import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.CarAuditLogItem;
import cz.fi.muni.pa165.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author rtrembecky
 */
@Repository
public class CarAuditLogItemDaoImpl implements CarAuditLogItemDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CarAuditLogItem> findAll() {
        return em.createQuery(
                "SELECT i FROM CarAuditLogItem i", CarAuditLogItem.class).getResultList();
    }

    @Override
    public CarAuditLogItem findById(UUID id) {
        return em.find(CarAuditLogItem.class, id);
    }

    @Override
    public List<CarAuditLogItem> findByCar(Car c) {
        return findByCar(c.getId());
    }

    @Override
    public List<CarAuditLogItem> findByCar(UUID id) {
        TypedQuery<CarAuditLogItem> query = em.createQuery(
                "SELECT i FROM CarAuditLogItem i WHERE i.car.id = :carId", CarAuditLogItem.class);
        query.setParameter("carId", id);
        return query.getResultList();
    }

    @Override
    public List<CarAuditLogItem> findByUser(UUID id) {
        TypedQuery<CarAuditLogItem> query = em.createQuery(
                "SELECT i FROM CarAuditLogItem i WHERE i.user.id = :userId", CarAuditLogItem.class);
        query.setParameter("userId", id);
        return query.getResultList();
    }

    @Override
    public List<CarAuditLogItem> findByUser(User u) {
        return findByUser(u.getId());
    }

    @Override
    public List<CarAuditLogItem> getRecordsCreatedBetween(Date from, Date to) {
        TypedQuery<CarAuditLogItem> query = em.createQuery(
                "SELECT r FROM CarAuditLogItem r WHERE r.created BETWEEN :fromDate AND :toDate ", CarAuditLogItem.class);
        query.setParameter("fromDate", from);
        query.setParameter("toDate", to);
        return query.getResultList();
    }

    @Override
    public List<CarAuditLogItem> findAllFromNewest() {
        return em.createQuery(
                "SELECT i FROM CarAuditLogItem i ORDER BY i.created DESC ", CarAuditLogItem.class).getResultList();
    }
}
