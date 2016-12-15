/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.springmvc.controllers;


import cz.fi.muni.pa165.api.dto.CarAuditLogItemDTO;
import cz.fi.muni.pa165.api.facade.CarAuditLogItemFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 *
 * @author charlliz
 */
@Controller
@RequestMapping("/records")
public class RecordsController {

    final static Logger log = LoggerFactory.getLogger(RecordsController.class);


    @Autowired
    private CarAuditLogItemFacade carAuditLogItemFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<CarAuditLogItemDTO> logItemDTOs = carAuditLogItemFacade.findAllFromNewest();

        model.addAttribute("logItems", logItemDTOs);
        return "records/list";
    }

//    /**
//     *
//     * @param id
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
//    public String view(@PathVariable UUID id, Model model) {
//        log.debug("view({})", id);
//        model.addAttribute("car", carFacade.findCarById(id));
//        return "car/view";
//    }
//
//    /**
//     * Show empty form for creating car
//     *
//     * @param model data to be displayed
//     * @return JSP page
//     */
//    @RequestMapping(value = "/new", method = RequestMethod.GET)
//    public String newCar(Model model) {
//        log.debug("new()");
//        model.addAttribute("createCar", new CarDTO());
//        return "car/new";
//    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String create(@Valid @ModelAttribute("createCar") CarDTO formBean, BindingResult bindingResult,
//                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
//        log.debug("create(formBean={})", formBean);
//        //in case of validation error forward back to the the form
//        if (bindingResult.hasErrors()) {
//            for (ObjectError ge : bindingResult.getGlobalErrors()) {
//                log.trace("ObjectError: {}", ge);
//            }
//            for (FieldError fe : bindingResult.getFieldErrors()) {
//                model.addAttribute(fe.getField() + "_error", true);
//                log.trace("FieldError: {}", fe);
//            }
//            return "car/new";
//        }
//        //create car
//        UUID id = carFacade.createCar(formBean);
//        //report success
//        redirectAttributes.addFlashAttribute("alert_success", "Car " + id + " was created");
//        return "redirect:" + uriBuilder.path("/car/list/all").toUriString();
//    }
    
}
