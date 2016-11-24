/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.model.dao;


import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.RentApplication;
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
public class RentApplicationDaoImpl implements RentApplicationDao {

    @PersistenceContext
    private EntityManager em;    
    
    @Override
    public List<RentApplication> findAll() {
        return em.createQuery(
                "SELECT r FROM RentApplication r", RentApplication.class).getResultList();
    }

    @Override
    public RentApplication findById(UUID id) {
       return em.find(RentApplication.class, id);
    }

    @Override
    public List<RentApplication> findByCar(Car c) {
        return findByCar(c.getId());
    }

    @Override
    public List<RentApplication> findByCar(UUID carId) {
        TypedQuery<RentApplication> query = em.createQuery(
                "SELECT r FROM RentApplication r WHERE r.car.id = :carid", RentApplication.class);

        query.setParameter("carid", carId);
        return query.getResultList();
    }

    @Override
    public List<RentApplication> findByUser(UUID userId) {
        TypedQuery<RentApplication> query = em.createQuery(
                "SELECT r FROM RentApplication r WHERE r.user.id = :userid", RentApplication.class);

        query.setParameter("userid", userId);
        return query.getResultList();
    }

    @Override
    public List<RentApplication> findByUser(User u) {
        return findByUser(u.getId());
    }

    @Override
    public List<RentApplication> getRecordsCreatedBetween(Date from, Date to) {
	TypedQuery<RentApplication> query = em.createQuery(
		"SELECT r FROM RentApplication r WHERE r.created BETWEEN :fromDate AND :toDate ", RentApplication.class);
	
        query.setParameter("fromDate", from);
	query.setParameter("toDate", to);
	return query.getResultList(); 
    }

    @Override
    public void create(RentApplication r) {
        em.persist(r);
    }

    @Override
    public void delete(RentApplication r) {
         em.remove(r);
    }
    
}
