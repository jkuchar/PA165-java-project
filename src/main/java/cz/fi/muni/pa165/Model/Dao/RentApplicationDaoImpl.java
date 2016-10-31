/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.Model.Dao;


import cz.fi.muni.pa165.Model.Entity.Car;
import cz.fi.muni.pa165.Model.Entity.RentApplication;
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
        TypedQuery<RentApplication> query = em.createQuery(
                "SELECT r FROM RentApplication r WHERE r.car = :carid", RentApplication.class);

        query.setParameter("carid", c);
        return query.getResultList();
    }

    @Override
    public List<RentApplication> findByUser(User u) {
         TypedQuery<RentApplication> query = em.createQuery(
                "SELECT r FROM RentApplication r WHERE r.user = :userid", RentApplication.class);

        query.setParameter("userid", u);
        return query.getResultList();
    }

    @Override
    public List<RentApplication> getRecordsCreatedBetween(Date from, Date to) {
	TypedQuery<RentApplication> query = em.createQuery(
		"SELECT r FROM RentApplication r WHERE r.created BETWEEN :fromDate AND :toDate ", RentApplication.class);
	
        query.setParameter("fromtDate", from);
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
