package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.PersonName;
import cz.fi.muni.pa165.model.entity.User;

import java.util.List;
import java.util.UUID;

/**
 * @author rtrembecky
 */
public interface UserService {

    /**
     * Retrieve a list of all users.
     * @return list of all users
     */
    List<User> findAll();

    /**
     * Retrieve user with corresponding id.
     * @param id is id of user
     * @return user with certain id
     */
    User findById(UUID id);

    /**
     * Retrieve user with corresponding name.
     * @param name is name of user
     * @return user with certain name
     */
    User findByName(PersonName name);

    /**
     * Method to retrieve user with corresponding email.
     * @param email is email of user
     * @return user with certain email
     */
    User findByEmail(String email);

    /**
     * Register a new user.
     * @param u is new user
     * @param unencryptedPassword is new password
     */
    void register(User u, String unencryptedPassword);

    /**
     * Delete user from database.
     * @param u is user to delete
     */
    void delete(User u);

    /**
     * Update user information in database.
     * @param u is user to update
     */
    void update(User u);

    /**
     * Authenticates a user.
     * @param email user to be authenticated
     * @param password user password
     * @return true is successful
     */
    boolean auth(String email, String password);
}
