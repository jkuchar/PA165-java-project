package cz.fi.muni.pa165.model.dao;

import cz.fi.muni.pa165.model.PersonName;
import cz.fi.muni.pa165.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    final static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

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
            log.warn("no user found with name " + name);
            log.warn("returning null");
            return null; // todo: really? or exception instead? Should be consistent with other methods.
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email).getSingleResult();
        } catch (NoResultException nre){
            log.warn("no user found with email " + email);
            log.warn("returning null");
            return null;
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
    
    @Override
    public void update(User c) {
            em.merge(c);
    }
}
