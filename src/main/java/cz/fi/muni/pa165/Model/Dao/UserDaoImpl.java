package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.User;
import cz.fi.muni.pa165.Model.PersonName;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * This file is part of PA165 school project.
 * @author jkuchar
 */
@Repository
final public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    /**
     * This constructor should be available if someone want to inject entity manager on their own
     */
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User findById(UUID id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByName(PersonName name) {
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class);
        try{
            return q.setParameter("name", name).getSingleResult();
        } catch (NoResultException nre) {
            return null; // todo: really? or exception instead? Should be consistent with other methods.
        }
    }

    @Override
    public void create(User c) {
        em.persist(c);
        // todo: investigate why this actually works? Thanks to @transactional? Why flush() is no needed?
    }

    @Override
    public void delete(User c) {
        em.remove(c);
    }
}
