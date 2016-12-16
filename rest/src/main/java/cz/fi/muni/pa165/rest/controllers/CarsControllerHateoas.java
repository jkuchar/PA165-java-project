/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.CarDTO;
import cz.fi.muni.pa165.api.facade.CarFacade;
import cz.fi.muni.pa165.rest.assemblers.CarResourceAssembler;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotModifiedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import org.dozer.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author charlliz
 */
@RestController
@RequestMapping("/cars_hateoas")
@ExposesResourceFor(CarDTO.class)
public class CarsControllerHateoas {

    final static Logger logger = LoggerFactory.getLogger(CarsControllerHateoas.class);

    @Inject
    private CarFacade carFacade;

    @Inject
    private CarResourceAssembler carResourceAssembler;

    /**
     *
     * Return list of cars - GET
     * 
     * @return HttpEntity<Resources<Resource<CarDTO>>>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resources<Resource<CarDTO>>> getCars() {
        
        logger.debug("rest getCars({}) hateoas");

        Collection<CarDTO> carDTO = carFacade.findAllCars();
        Collection<Resource<CarDTO>> carResourceCollection = new ArrayList();

        for (CarDTO c : carDTO) {
            carResourceCollection.add(carResourceAssembler.toResource(c));
        }

        Resources<Resource<CarDTO>> carsResources = new Resources<Resource<CarDTO>>(carResourceCollection);
        carsResources.add(linkTo(CarsControllerHateoas.class).withSelfRel());

        return new ResponseEntity<Resources<Resource<CarDTO>>>(carsResources, HttpStatus.OK);

    }
    
     /**
     *
     * Get list of cars  
     * 
     * GET http://localhost:8080/carpark-rest/cars_hateoas/cached 
     * 
     * @return HttpEntity<Resources<Resource<CarDTO>>>
     */
    @RequestMapping(value = "/cached", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resources<Resource<CarDTO>>> getCarsCached(WebRequest webRequest) {
        
        logger.debug("rest getCars({}) hateoas cached version");
       
        final Collection<CarDTO> carsDTO = carFacade.findAllCars();
        final Collection<Resource<CarDTO>> carResourceCollection = new ArrayList();

        for (CarDTO c : carsDTO) {
            carResourceCollection.add(carResourceAssembler.toResource(c));
        }

        Resources<Resource<CarDTO>> carsResources = new Resources(carResourceCollection);
        carsResources.add(linkTo(CarsControllerHateoas.class).withSelfRel());

        final StringBuffer eTag = new StringBuffer("\"");
        eTag.append(Integer.toString(carsResources.hashCode()));
        eTag.append('\"');
        
        if (webRequest.checkNotModified(eTag.toString())){
            throw new ResourceNotModifiedException();
        }
                
        return ResponseEntity.ok().eTag(eTag.toString()).body(carsResources);
    }

    /**
     *
     * Get car by id
     * 
     * @param id is id of car
     * @return HttpEntity<Resource<CarDTO>>
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resource<CarDTO>> getCar(@PathVariable("id") UUID id) throws Exception {
        
        logger.debug("rest getCar({}) hateoas", id);

        try {
        CarDTO carDTO = carFacade.findCarById(id);
            Resource<CarDTO> resource = carResourceAssembler.toResource(carDTO);
            return new ResponseEntity<Resource<CarDTO>>(resource, HttpStatus.OK);    
        } catch (Exception ex){
            throw new ResourceNotFoundException();
        }
    }

}
