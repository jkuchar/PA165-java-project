package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.ApplicationRejectedRecord;
import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.User;
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
public class ApplicationRejectedRecordDaoImpl implements ApplicationRejectedRecordDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ApplicationRejectedRecord> findAll() {
        return em.createQuery(
                "SELECT r FROM ApplicationRejectedRecord r", ApplicationRejectedRecord.class).getResultList();
    }

    @Override
    public ApplicationRejectedRecord findById(UUID id) {
        return em.find(ApplicationRejectedRecord.class, id);
    }

    @Override
    public List<ApplicationRejectedRecord> findByCar(Car c) {
        TypedQuery<ApplicationRejectedRecord> query = em.createQuery(
                "SELECT r FROM ApplicationRejectedRecord r WHERE r.car = :car", ApplicationRejectedRecord.class);

        query.setParameter("car", c);
        return query.getResultList();
    }

    @Override
    public List<ApplicationRejectedRecord> findByUser(User u) {
        TypedQuery<ApplicationRejectedRecord> query = em.createQuery(
                "SELECT r FROM ApplicationRejectedRecord r WHERE r.user = :user", ApplicationRejectedRecord.class);

        query.setParameter("user", u);
        return query.getResultList();
    }
    
    @Override
    public List<ApplicationRejectedRecord> getRecordsCreatedBetween(Date from, Date to) {
	TypedQuery<ApplicationRejectedRecord> query = em.createQuery(
		"SELECT r FROM ApplicationApprovedRecord r WHERE r.created BETWEEN :fromDate AND :toDate ", ApplicationRejectedRecord.class);
	
        query.setParameter("fromDate", from);
	query.setParameter("toDate", to);
	return query.getResultList(); 
    }

    @Override
    public void create(ApplicationRejectedRecord r) {
        em.persist(r);
    }

    @Override
    public void delete(ApplicationRejectedRecord r) {
        em.remove(r);
    }
}
