package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.CarAuditLogItem;
import cz.fi.muni.pa165.Model.Entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author rtrembecky
 */
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
        TypedQuery<CarAuditLogItem> query = em.createQuery(
                "SELECT i FROM CarAuditLogItem i WHERE i.car = :car", CarAuditLogItem.class);
        query.setParameter("car", c);
        return query.getResultList();
    }

    @Override
    public List<CarAuditLogItem> findByUser(User u) {
        TypedQuery<CarAuditLogItem> query = em.createQuery(
                "SELECT i FROM CarAuditLogItem i WHERE i.user = :user", CarAuditLogItem.class);
        query.setParameter("user", u);
        return query.getResultList();
    }

    @Override
    public List<CarAuditLogItem> getRecordsCreatedBetween(Date from, Date to) {
        TypedQuery<CarAuditLogItem> query = em.createQuery(
                "SELECT r FROM CarAuditLogItem r WHERE r.created BETWEEN :fromDate AND :toDate ", CarAuditLogItem.class);
        query.setParameter("fromDate", from);
        query.setParameter("toDate", to);
        return query.getResultList();
    }
}
