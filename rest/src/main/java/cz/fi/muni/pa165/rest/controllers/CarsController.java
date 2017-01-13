/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.CarCreateDTO;
import cz.fi.muni.pa165.api.dto.CarDTO;
import cz.fi.muni.pa165.api.facade.CarFacade;
import cz.fi.muni.pa165.rest.ApiUris;
import cz.fi.muni.pa165.rest.exceptions.InvalidParameterException;
import cz.fi.muni.pa165.rest.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author charlliz
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_CARS)
public class CarsController {

    final static Logger logger = LoggerFactory.getLogger(CarsController.class);

    @Autowired
    private CarFacade carFacade;

    /**
     * Return list of all cars
     *
     * @return list of CarDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CarDTO> getCars() {
        logger.debug("rest getCars()");
        return carFacade.findAllCars();
    }

    /**
     *
     * Return specific car by id 
     *
     * @param id is id of car
     * @return CarDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CarDTO getCar(@PathVariable("id") UUID id) throws Exception {
        logger.debug("rest getCar({})", id);

        try {
            CarDTO carDTO = carFacade.findCarById(id);
            return carDTO;
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }

    }

    /**
     * Create new car 
     * 
     * @param car CarCreateDTO with required fields
     * @return created car
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CarDTO createCar(@RequestBody CarCreateDTO car) throws Exception {

        logger.debug("rest createCar()");

        try {
            UUID id = carFacade.createCar(car);
            return carFacade.findCarById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }
    
    /**
     * 
     * Change state of car
     * 
     * @param id is id of car
     * @param action one of SERVICE, DISCARD, OK 
     * @return car with changed state
     * @throws Exception 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CarDTO changeCarState(@PathVariable("id") UUID id, @RequestParam("action") String action)throws Exception {

        logger.debug("rest changeCarState()");
        
        if (action.equalsIgnoreCase("SERVICE")) {
            carFacade.serviceCar(id);
        } else if (action.equalsIgnoreCase("DISCARD")) {
            carFacade.discardCar(id);
        } else if (action.equalsIgnoreCase("OK")) {
            carFacade.finishService(id);
        } else {
            throw new InvalidParameterException();
        }

        return carFacade.findCarById(id);
    } 

}
