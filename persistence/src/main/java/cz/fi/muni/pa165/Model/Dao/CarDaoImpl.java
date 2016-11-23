package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.CarState;
import cz.fi.muni.pa165.Model.Entity.Car;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Future;
import java.util.List;
import java.util.UUID;

/**
 * Created by SARMIR on 29. 10. 2016.
 * @author jakubsarmir
 */
@Repository
public class CarDaoImpl implements CarDao{

    @PersistenceContext
    private EntityManager em;

    public CarDaoImpl(EntityManager em) {this.em = em; }

    @Override
    public List<Car> findAll() {
        return em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }

    @Override
    public Car findById(UUID id) {
        return em.find(Car.class, id);
    }

    @Override
    public Car findBySerialNumber(String serialNumber) {
        try {
            return em.createQuery("SELECT c FROM Car c WHERE c.serialNumber = :serial_number",Car.class).setParameter("serial_number", serialNumber).getSingleResult();
        } catch (NoResultException nre){
            return null;
        }
    }

    @Override
    public Car findByRegPlateNumber(String regPlateNumber) {
        try {
            return em.createQuery("SELECT c FROM Car c WHERE c.regPlateNumber = :reg_plate_number",Car.class).setParameter("reg_plate_number", regPlateNumber).getSingleResult();
        } catch (NoResultException nre){
            return null;
        }
    }

    @Override
    public List<Car> findByState(CarState state) {
        try {
            return em.createQuery("SELECT c FROM Car c WHERE c.state = :car_state", Car.class).setParameter("car_state", state).getResultList();
        } catch (NoResultException nre){
            return null;
        }
    }

    @Override
    public void create(Car c) {
        em.persist(c);
    }

    @Override
    public void delete(Car c) {
        em.remove(em.merge(c));
    }
    
    @Override
    public void update(Car c) {
            em.merge(c);
    }

    @Override
    public List<Car> getAllCarsByManufacturer(String manufacturer) {
        TypedQuery<Car> query = em.createQuery(
            "SELECT r FROM Car r WHERE r.manufacturer = :manufacturer", Car.class);

        query.setParameter("manufacturer", manufacturer);
        return query.getResultList();
    }

    @Override
    public List<Car> getAllCarsByType(String type) {
       TypedQuery<Car> query = em.createQuery(
            "SELECT r FROM Car r WHERE r.type = :type", Car.class);

        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    public List<Car> getAllCarsBySeats(int seats) {
       TypedQuery<Car> query = em.createQuery(
            "SELECT r FROM Car r WHERE r.seats = :seats", Car.class);

        query.setParameter("seats", seats);
        return query.getResultList();
    }
}
