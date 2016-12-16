/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.ApplicationRejectedRecordDTO;
import cz.fi.muni.pa165.api.facade.ApplicationRejectedRecordFacade;
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
@RequestMapping(ApiUris.ROOT_URI_REJECTED)
public class ApplicationRejectedRecordController {

    final static Logger logger = LoggerFactory.getLogger(ApplicationRejectedRecordController.class);

    @Inject
    private ApplicationRejectedRecordFacade applicationRejectedRecordFacade;

    /**
     * Get list of rejected records - GET
     * curl -i -X GET http://localhost:8080/carpark-rest/records/rejected
     *
     * @return ApplicationRejectedRecordDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ApplicationRejectedRecordDTO> getAllRejectedRecords() {
        logger.debug("rest getAllRejectedRecords()");
        return applicationRejectedRecordFacade.findAllRecords();
    }

    /**
     *
     * Get ApplicationRejectedRecord by id - GET
     * curl -i -X GET http://localhost:8080/carpark-rest/records/rejected/f0f2d3b0-c3aa-11e6-a4a6-cec0c932ce01
     *
     * @param id is id of record
     * @return ApplicationRejectedRecordDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ApplicationRejectedRecordDTO getRejectedRecord(@PathVariable("id") UUID id) throws Exception {
        logger.debug("rest getRejectedRecord({})", id);

        try {
            ApplicationRejectedRecordDTO recordDTO = applicationRejectedRecordFacade.findRecordById(id);
            return recordDTO;
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }

    }

    /**
     * Create new record - POST
     * 
     * http://localhost:8080/carpark-rest/records/rejected/create
     * 
     * @param record ApplicationRejectedRecordDTO with required fields
     * @return created record
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ApplicationRejectedRecordDTO createRejectedRecord(@RequestBody ApplicationRejectedRecordDTO record) throws Exception {

        logger.debug("rest createRejectedRecord()");

        try {
            UUID id = applicationRejectedRecordFacade.create(record);
            return applicationRejectedRecordFacade.findRecordById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }
    

}