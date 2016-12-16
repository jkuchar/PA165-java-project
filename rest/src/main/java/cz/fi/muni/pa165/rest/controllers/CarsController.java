/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.CarDTO;
import cz.fi.muni.pa165.api.facade.CarFacade;
import cz.fi.muni.pa165.rest.ApiUris;
import cz.fi.muni.pa165.rest.exceptions.InvalidParameterException;
import cz.fi.muni.pa165.rest.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.service.exceptions.CarParkServiceException;
import java.util.List;
import java.util.UUID;
import org.dozer.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author charlliz
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_CARS)
public class CarsController {

    final static Logger logger = LoggerFactory.getLogger(CarsController.class);

    @Inject
    private CarFacade carFacade;

    /**
     * Get list of Cars - GET
     * curl -i -X GET http://localhost:8080/carpark-rest/cars
     *
     * @return CarDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CarDTO> getCars() {
        logger.debug("rest getCars()");
        return carFacade.findAllCars();
    }

    /**
     *
     * Get Car by id - GET
     * curl -i -X GET http://localhost:8080/carpark-rest/cars/f0f2d3b0-c3aa-11e6-a4a6-cec0c932ce01
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
     * Create new car - POST
     * 
     * http://localhost:8080/carpark-rest/cars/create
     * 
     * @param car CarDTO with required fields
     * @return created car
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CarDTO createCar(@RequestBody CarDTO car) throws Exception {

        logger.debug("rest createCar()");

        try {
            UUID id = carFacade.createCar(car);
            return carFacade.findCarById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }

    /**
     * Update state of the car - discard 
     * curl -X PUT -i -H
     * http://localhost:8080/carpark-rest/cars/f0f2d3b0-c3aa-11e6-a4a6-cec0c932ce01
     *
     * @param id is id of car to be discarded
     * @return the updated car
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CarDTO discardCar(@PathVariable("id") UUID id) throws Exception {

        logger.debug("rest discardCar({})", id);

        try {
            carFacade.discardCar(id);
            return carFacade.findCarById(id);
        } catch (CarParkServiceException ex) {
            throw new InvalidParameterException();
        }

    }
    
     /**
     * Update state of the car - servicing - PUT
     * 
     * http://localhost:8080/carpark-rest/cars/f0f2d3b0-c3aa-11e6-a4a6-cec0c932ce01
     *
     * @param id is id of car to be serviced
     * @return the updated car
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CarDTO serviceCar(@PathVariable("id") UUID id) throws Exception {

        logger.debug("rest serviceCar({})", id);

        try {
            carFacade.serviceCar(id);
            return carFacade.findCarById(id);
        } catch (CarParkServiceException ex) {
            throw new InvalidParameterException();
        }

    }

}
