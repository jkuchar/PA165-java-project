package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.UserDTO;
import cz.fi.muni.pa165.api.facade.UserFacade;
import cz.fi.muni.pa165.rest.ApiUris;
import com.fasterxml.jackson.core.JsonProcessingException;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotFoundException;
import java.util.Collection;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rtrembecky
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_USERS)
public class UsersController {

    final static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<UserDTO> getUsers() throws JsonProcessingException {

        logger.debug("rest getUsers()");
        return userFacade.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUser(@PathVariable("id") UUID id) throws ResourceNotFoundException {

        logger.debug("rest getUser({})", id);
        UserDTO userDTO = userFacade.findById(id);
        if (userDTO == null){
            throw new ResourceNotFoundException();
        }
        return userDTO;
    }
}