package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.UserDTO;
import cz.fi.muni.pa165.api.facade.UserFacade;
import cz.fi.muni.pa165.model.PersonName;
import cz.fi.muni.pa165.model.Role;
import cz.fi.muni.pa165.model.entity.User;
import cz.fi.muni.pa165.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author rtrembecky
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    private final BeanMappingService beanMappingService;

    private final UserService userService;

    @Autowired
    public UserFacadeImpl (BeanMappingService beanMappingService, UserService userService) {
        this.beanMappingService = beanMappingService;
        this.userService = userService;
    }

    @Override
    public List<UserDTO> findAll() {
        return beanMappingService.mapTo(userService.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO findById(UUID id) {
        return beanMappingService.mapTo(userService.findById(id), UserDTO.class);
    }

    @Override
    public UserDTO findByName(String firstName, String secondName) {
        return beanMappingService.mapTo(userService.findByName(new PersonName(firstName, secondName)), UserDTO.class);
    }

    @Override
    public UUID register(UserDTO u) {
        PersonName personName = new PersonName(u.getFirstName(), u.getLastName());
        Role role;
        switch (u.getRole()) {
            case ADMIN: role = Role.ADMIN;
                break;
            case MANAGER: role = Role.MANAGER;
                break;
            case USER: role = Role.USER;
                break;
            default: role = Role.USER;
        }
        User user = new User(personName, role, u.getEmail(), u.getCreated());
        userService.register(user);
        return user.getId();
    }

    @Override
    public void delete(UserDTO u) {
        User user = userService.findById(u.getId());
        userService.delete(user);
    }

    @Override
    public void update(UserDTO u) {
        User user = userService.findById(u.getId());
        user.setName(new PersonName(u.getFirstName(), u.getLastName()));
        user.setEmail(u.getEmail());
        Role role;
        switch (u.getRole()) {
            case ADMIN: role = Role.ADMIN;
            break;
            case MANAGER: role = Role.MANAGER;
            break;
            case USER: role = Role.USER;
            break;
            default: role = Role.USER;
        }
        user.setRole(role);
        userService.update(user);
    }
}
