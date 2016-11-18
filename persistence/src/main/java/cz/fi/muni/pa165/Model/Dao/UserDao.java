package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.User;
import cz.fi.muni.pa165.Model.PersonName;

import java.util.List;
import java.util.UUID;

/**
 * This file is part of PA165 school project.
 * @author jkuchar
 */
public interface UserDao {

    /**
     * Method retrieve all users from db
     * @return list of all users
     */
    List<User> findAll();

    /**
     * Method retrieve item with corresponding id
     * @param id is id of user
     * @return user with certain id
     */
    User findById(UUID id);

    /**
     * Method retrieve user with corresponding name.
     * @param name is name of user
     * @return return user with certian name
     */
    User findByName(PersonName name);

    /**
     * Method insert new user into db.
     * @param c is new user
     */
    void create(User c);

    /**
     * Method delete user from db.
     * @param c is user to delete
     */
    void delete(User c);

    /**
     * Method is used for updating information of user. 
     * @param c is user to update
     */
    void update(User c); 

}
