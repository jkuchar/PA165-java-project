/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.rest.assemblers;

import cz.fi.muni.pa165.api.dto.CarDTO;
import cz.fi.muni.pa165.controllers.CarsControllerHateoas;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import static org.springframework.hateoas.jaxrs.JaxRsLinkBuilder.linkTo;
import org.springframework.stereotype.Component;

/**
 *
 * @author charlliz
 */
@Component
public class CarResourceAssembler implements ResourceAssembler<CarDTO, Resource<CarDTO>> {

    @Override
    public Resource<CarDTO> toResource(CarDTO carDTO) {
        UUID id = carDTO.getId();
        Resource<CarDTO> productResource = new Resource<CarDTO>(carDTO);

        try {
            productResource.add(linkTo(CarsControllerHateoas.class).slash(carDTO.getId()).withSelfRel());
            productResource.add(linkTo(CarsControllerHateoas.class).slash(carDTO.getId()).withRel("DELETE"));

        } catch (Exception ex) {
            Logger.getLogger(CarResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from ProductsControllerHateoas", ex);
        }

        return productResource;
    }
}
