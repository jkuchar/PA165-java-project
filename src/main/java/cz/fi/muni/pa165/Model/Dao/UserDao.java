package cz.fi.muni.pa165.Model.Dao;

import cz.fi.muni.pa165.Model.Entity.User;
import cz.fi.muni.pa165.Model.PersonName;

import java.util.List;
import java.util.UUID;

/**
 * This file is part of PA165 school project.
 */
public interface UserDao {

    List<User> findAll();
    User findById(UUID id);
    User findByName(PersonName name);

    void create(User c);
    void delete(User c);

}
