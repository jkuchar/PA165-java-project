/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.ReturnRecord;
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
public class ReturnRecordDaoImpl implements ReturnRecordDao{

    @PersistenceContext
    private EntityManager em;    
    
    @Override
    public List<ReturnRecord> findAll() {
        return em.createQuery(
                "SELECT r FROM ReturnRecord r", ReturnRecord.class).getResultList();
    }

    @Override
    public ReturnRecord findById(UUID id) {
       return em.find(ReturnRecord.class, id);
    }

    @Override
    public List<ReturnRecord> findByCar(Car c) {
        TypedQuery<ReturnRecord> query = em.createQuery(
                "SELECT r FROM ReturnRecord r WHERE r.car = :carid", ReturnRecord.class);

        query.setParameter("carid", c);
        return query.getResultList();
    }

    @Override
    public List<ReturnRecord> findByUser(User u) {
         TypedQuery<ReturnRecord> query = em.createQuery(
                "SELECT r FROM ReturnRecord r WHERE r.user = :userid", ReturnRecord.class);

        query.setParameter("userid", u);
        return query.getResultList();
    }

    @Override
    public List<ReturnRecord> getRecordsCreatedBetween(Date from, Date to) {
	TypedQuery<ReturnRecord> query = em.createQuery(
		"SELECT r FROM ReturnRecord r WHERE r.created BETWEEN :fromDate AND :toDate ", ReturnRecord.class);
	
        query.setParameter("fromDate", from);
	query.setParameter("toDate", to);
	return query.getResultList(); 
    }

    @Override
    public void create(ReturnRecord r) {
        em.persist(r);
    }

    @Override
    public void delete(ReturnRecord r) {
         em.remove(r);
    }
         
}
