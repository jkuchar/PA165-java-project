/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.springmvc.controllers;


import cz.fi.muni.pa165.api.dto.CarAuditLogItemDTO;
import cz.fi.muni.pa165.api.dto.CarLogStateDTO;
import cz.fi.muni.pa165.api.facade.CarAuditLogItemFacade;
import cz.fi.muni.pa165.model.CarAuditLogItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

/**
 * @author jkuchar
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


    @RequestMapping(value = "/add/{carId}", method = RequestMethod.GET)
    public String create(@PathVariable("carId") UUID carId, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        CarLogStateDTO logState = carAuditLogItemFacade.findLogState(carId);

        model.addAttribute("carId", carId);
        if(logState != null) {
            model.addAttribute("currentState", logState.getTypeName());
            model.addAttribute("currentRecordId", logState.getRecordId());
            model.addAttribute("possibleNextSteps", logState.getPossibleStates());
        } else {
            model.addAttribute("currentState", null);
            model.addAttribute("currentRecordId", null);
            model.addAttribute("possibleNextSteps", carAuditLogItemFacade.getInitialStates() /* returns also DTOs */);
        }

        return "records/select";
    }

    @RequestMapping(value = "/add/{carId}/{recordType}", method = RequestMethod.GET)
    public String create(@PathVariable("carId") UUID carId, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, @PathVariable("recordType") String recordType) {
        // validate recordType for current state

        // redirect to proper particular record controller

        return "redirect:" + uriBuilder.path("TODO TODO TODO").toUriString();
    }
    
}
