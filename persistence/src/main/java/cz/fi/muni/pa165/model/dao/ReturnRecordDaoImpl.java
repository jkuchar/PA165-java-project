/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.model.dao;

import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.ReturnRecord;
import cz.fi.muni.pa165.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        return findByCar(c.getId());
    }

    @Override
    public List<ReturnRecord> findByCar(UUID carId) {
        TypedQuery<ReturnRecord> query = em.createQuery(
                "SELECT r FROM ReturnRecord r WHERE r.car.id = :carid", ReturnRecord.class);

        query.setParameter("carid", carId);
        return query.getResultList();
    }

    @Override
    public List<ReturnRecord> findByUser(User u) {
        return findByUser(u.getId());
    }

    @Override
    public List<ReturnRecord> findByUser(UUID userId) {
        TypedQuery<ReturnRecord> query = em.createQuery(
                "SELECT r FROM ReturnRecord r WHERE r.user.id = :userid", ReturnRecord.class);

        query.setParameter("userid", userId);
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
