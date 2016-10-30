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
    public List<CarAuditLogItem> findById(UUID id) {
        TypedQuery<CarAuditLogItem> query = em.createQuery(
                "SELECT i FROM CarAuditLogItem i WHERE i = :itemId", CarAuditLogItem.class);

        query.setParameter("itemId", id);
        return query.getResultList();
    }

    @Override
    public List<CarAuditLogItem> findByCar(Car c) {
        TypedQuery<CarAuditLogItem> query = em.createQuery(
                "SELECT i FROM CarAuditLogItem i WHERE i.car = :carId", CarAuditLogItem.class);

        query.setParameter("carId", c.getId());
        return query.getResultList();
    }

    @Override
    public List<CarAuditLogItem> findByUser(User u) {
        TypedQuery<CarAuditLogItem> query = em.createQuery(
                "SELECT i FROM CarAuditLogItem i WHERE i.user = :userId", CarAuditLogItem.class);

        query.setParameter("userId", u.getId());
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

    @Override
    public void create(CarAuditLogItem i) {
        em.persist(i);
    }

    @Override
    public void delete(CarAuditLogItem i) {
        em.remove(i);
    }
}
