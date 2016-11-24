package cz.fi.muni.pa165.model.dao;

import cz.fi.muni.pa165.model.PersonName;
import cz.fi.muni.pa165.model.entity.User;

import java.util.List;
import java.util.UUID;

/**
 * This file is part of PA165 school project.
 * @author jkuchar
 */
public interface UserDao {

    /**
     * Method to retrieve all users from db
     * @return list of all users
     */
    List<User> findAll();

    /**
     * Method to retrieve item with corresponding id
     * @param id is id of user
     * @return user with certain id
     */
    User findById(UUID id);

    /**
     * Method to retrieve user with corresponding name.
     * @param name is name of user
     * @return return user with certain name
     */
    User findByName(PersonName name);

    /**
     * Method to insert new user into db.
     * @param c is new user
     */
    void create(User c);

    /**
     * Method to delete user from db.
     * @param c is user to delete
     */
    void delete(User c);

    /**
     * Method to update information about user in db.
     * @param c is user to update
     */
    void update(User c); 

}
