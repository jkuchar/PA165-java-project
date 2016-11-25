package cz.fi.muni.pa165.api.facade;

import java.util.List;
import java.util.UUID;
import cz.fi.muni.pa165.api.dto.UserDTO;

/**
 * @author rtrembecky
 */
public interface UserFacade {

    /**
     * Retrieve a list of all users.
     * @return list of all users
     */
    List<UserDTO> findAll();

    /**
     * Retrieve user with corresponding id.
     * @param id is id of user
     * @return user with certain id
     */
    UserDTO findById(UUID id);

    /**
     * Retrieve user with corresponding name.
     * @param firstName is the first name of the user
     * @param secondName is the second name of the user
     * @return return user with certain name
     */
    UserDTO findByName(String firstName, String secondName);

    /**
     * Register a new user.
     * @param u is new user
     */
    UUID register(UserDTO u);

    /**
     * Delete user from database.
     * @param u is user to delete
     */
    void delete(UserDTO u);

    /**
     * Update user information in database.
     * @param u is user to update
     */
    void update(UserDTO u);
}