/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.springmvc.controllers;


import cz.fi.muni.pa165.api.dto.CarCreateDTO;
import cz.fi.muni.pa165.api.dto.CarDTO;
import cz.fi.muni.pa165.api.facade.CarAuditLogItemFacade;
import cz.fi.muni.pa165.api.facade.CarFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import cz.fi.muni.pa165.enums.CarState;
import cz.fi.muni.pa165.service.exceptions.CarParkServiceException;
import org.dozer.inject.Inject;

/**
 *
 * @author charlliz
 */
@Controller
@RequestMapping("/car")
public class CarController {

    final static Logger log = LoggerFactory.getLogger(CarController.class);


    @Autowired
    private CarFacade carFacade;

    @Autowired
    private CarAuditLogItemFacade carAuditLogItemFacade;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        return list("all", model);
    }

    @RequestMapping(value = "/list/{filter}", method = RequestMethod.GET)
    public String list(@PathVariable String filter, Model model) {
        List<CarDTO> cars;
        switch (filter) {
            case "all":
                cars = carFacade.findAllCars();
                break;
            case "ok":
                cars = carFacade.getAllCarsByState(CarState.OK);
                break;
            case "servicing":
                cars = carFacade.getAllCarsByState(CarState.SERVICING);
                break;
            case "discarded":
                cars = carFacade.getAllCarsByState(CarState.DISCARDED);
                break;
            default:
                cars = new ArrayList<>();
                model.addAttribute("danger", "Unknown filter " + filter);
        }
        model.addAttribute("cars", cars);
        return "car/list";
    }
    
    /**
     * 
     * @param id
     * @param model
     * @return 
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable UUID id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("car", carFacade.findCarById(id));
        model.addAttribute("logItems", carAuditLogItemFacade.findByCar(id));
        return "car/view";
    }

    /**
     * Show empty form for creating car
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCar(Model model) {
        log.debug("new()");
        model.addAttribute("createCar", new CarCreateDTO());
        return "car/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("createCar") CarCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(formBean={})", formBean);
        //in case of validation error forward back to the the form
        if(ValidationTools.validateRequestAndModel(bindingResult, model, new String[] {"manufacturer", "model", "registrationPlate", "serialNumber", "seats"}, log)) {

            return "car/new";
        }
        //create car
        UUID id = carFacade.createCar(formBean);
        //report success
        redirectAttributes.addFlashAttribute("success", "Car " + id + " was created");
        return "redirect:" + uriBuilder.path("/car/list/all").toUriString();
    }
    
    @RequestMapping(value = "/service/{id}", method = RequestMethod.POST)
    public String service(@PathVariable UUID id, Model model,UriComponentsBuilder uriBuilder,RedirectAttributes redirectAttributes) {
        try {
            carFacade.serviceCar(id);
            redirectAttributes.addFlashAttribute("success", "Car with id "+id+" was serviced.");
        } catch (CarParkServiceException ex) {
            log.warn("car cant be seviced {}",id);
            redirectAttributes.addFlashAttribute("danger", "Car with id  "+id+" was not set as serviced. "+ex.getMessage());
        }
        return "redirect:" + uriBuilder.path("/car/view/{id}").buildAndExpand(id).encode().toUriString();
    }
    
    @RequestMapping(value = "/ok/{id}", method = RequestMethod.POST)
    public String finishService(@PathVariable UUID id, Model model,UriComponentsBuilder uriBuilder,RedirectAttributes redirectAttributes) {
        try {
            carFacade.finishService(id);
            redirectAttributes.addFlashAttribute("success", "Service of the car with id "+id+" was canceled.");
        } catch (CarParkServiceException ex) {
            log.warn("car cant be seviced {}",id);
            redirectAttributes.addFlashAttribute("danger", "Service of the car with id  "+id+" was not canceled. "+ex.getMessage());
        }
        return "redirect:" + uriBuilder.path("/car/view/{id}").buildAndExpand(id).encode().toUriString();
    }
    
    @RequestMapping(value = "/discard/{id}", method = RequestMethod.POST)
    public String discard(@PathVariable UUID id, Model model,UriComponentsBuilder uriBuilder,RedirectAttributes redirectAttributes) {
        try {
            carFacade.discardCar(id);
            redirectAttributes.addFlashAttribute("success", "Car with id "+id+" was discarded.");
        } catch (CarParkServiceException ex) {
            log.warn("car cant be seviced {}",id);
            redirectAttributes.addFlashAttribute("danger", "Car with id  "+id+" was not discarded. "+ex.getMessage());
        }
        return "redirect:" + uriBuilder.path("/car/view/{id}").buildAndExpand(id).encode().toUriString();
    }
    
}
