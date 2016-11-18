/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.RentRecord;
import cz.fi.muni.pa165.Model.Entity.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author charlliz
 */
@Repository
public class RentRecordDaoImpl implements RentRecordDao{

    @PersistenceContext
    private EntityManager em;    
    
    @Override
    public List<RentRecord> findAll() {
        return em.createQuery(
                "SELECT r from RentRecord r", RentRecord.class).getResultList();
    }

    @Override
    public RentRecord findById(UUID id) {
       return em.find(RentRecord.class, id);
    }

    @Override
    public List<RentRecord> findByCar(Car c) {
        TypedQuery<RentRecord> query = em.createQuery(
                "SELECT r FROM RentRecord r WHERE r.car = :carid", RentRecord.class);

        query.setParameter("carid", c);
        return query.getResultList();
    }

    @Override
    public List<RentRecord> findByUser(User u) {
         TypedQuery<RentRecord> query = em.createQuery(
                "SELECT r FROM RentRecord r WHERE r.user = :userid", RentRecord.class);

        query.setParameter("userid", u);
        return query.getResultList();
    }

    @Override
    public List<RentRecord> getRecordsCreatedBetween(Date from, Date to) {
	TypedQuery<RentRecord> query = em.createQuery(
		"SELECT r FROM RentRecord r WHERE r.created BETWEEN :fromDate AND :toDate ", RentRecord.class);
	
        query.setParameter("fromDate", from);
	query.setParameter("toDate", to);
	return query.getResultList(); 
    }

    @Override
    public void create(RentRecord r) {
        em.persist(r);
    }

    @Override
    public void delete(RentRecord r) {
         em.remove(r);
    }
        
}
