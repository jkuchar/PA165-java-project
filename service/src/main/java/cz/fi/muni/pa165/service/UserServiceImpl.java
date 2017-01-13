package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.PersonName;
import cz.fi.muni.pa165.model.dao.UserDao;
import cz.fi.muni.pa165.model.entity.User;
import cz.fi.muni.pa165.service.security.PBKDF2PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author rtrembecky
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl (UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userDao.findById(id);
    }

    @Override
    public User findByName(PersonName name) {
        return userDao.findByName(name);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void register(User u, String unencryptedPassword) {
        String passwordHash = PBKDF2PasswordEncoder.createHash(unencryptedPassword);
        u.setPasswordHash(passwordHash);
        userDao.create(u);
    }

    @Override
    public void delete(User u) {
        userDao.delete(u);
    }

    @Override
    public void update(User u) {
        userDao.update(u);
    }

    @Override
    public boolean validate(String email, String password) {
        User user = userDao.findByEmail(email);
        if (user == null)
            return false;
        return PBKDF2PasswordEncoder.validatePassword(password, user.getPasswordHash());
    }
}
