/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.model.dao;

import cz.fi.muni.pa165.model.entity.ApplicationApprovedRecord;
import cz.fi.muni.pa165.model.entity.Car;
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
public class ApplicationApprovedRecordDaoImpl implements ApplicationApprovedRecordDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ApplicationApprovedRecord> findAll() {
        return em.createQuery(
                "SELECT r FROM ApplicationApprovedRecord r", ApplicationApprovedRecord.class)
                .getResultList();
    }

    @Override
    public ApplicationApprovedRecord findById(UUID id) {
        return em.find(ApplicationApprovedRecord.class, id);
    }

    @Override
    public List<ApplicationApprovedRecord> findByCar(Car c) {
        TypedQuery<ApplicationApprovedRecord> query = em.createQuery(
                "SELECT r FROM ApplicationApprovedRecord r WHERE r.car = :carid", ApplicationApprovedRecord.class);

        query.setParameter("carid", c);
        return query.getResultList();
    }

    @Override
    public List<ApplicationApprovedRecord> findByUser(User u) {
         TypedQuery<ApplicationApprovedRecord> query = em.createQuery(
                "SELECT r FROM ApplicationApprovedRecord r WHERE r.user = :userid", ApplicationApprovedRecord.class);

        query.setParameter("userid", u);
        return query.getResultList();
    }

    @Override
    public List<ApplicationApprovedRecord> getRecordsCreatedBetween(Date from, Date to) {
	TypedQuery<ApplicationApprovedRecord> query = em.createQuery(
		"SELECT r FROM ApplicationApprovedRecord r WHERE r.created BETWEEN :fromDate AND :toDate ", ApplicationApprovedRecord.class);
	
        query.setParameter("fromDate", from);
	query.setParameter("toDate", to);
	return query.getResultList(); 
    }
   
    @Override
    public void create(ApplicationApprovedRecord r) {
        em.persist(r);
    }

    @Override
    public void delete(ApplicationApprovedRecord r) {
        em.remove(r);
    }

}
