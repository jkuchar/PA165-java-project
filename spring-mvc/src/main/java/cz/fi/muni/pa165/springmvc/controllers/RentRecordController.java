/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.springmvc.controllers;

import cz.fi.muni.pa165.api.facade.RentApplicationFacade;
import cz.fi.muni.pa165.api.facade.RentRecordFacade;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author charlliz
 */
@Controller
@RequestMapping("/rent")
public class RentRecordController {

    final static Logger log = LoggerFactory.getLogger(RentRecordController.class);


    @Autowired
    private RentRecordFacade rentFacade;

    /**
     * Shows a list of all rent records.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("records", rentFacade.findAll());
        return "rent/list";
    }
    
     /**
     * Show detail of rent record with certain id
     * 
     * @param id is id of rent record
     * @param model dala to display
     * @return 
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable UUID id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("record", rentFacade.findById(id));
        return "rent/view";
    }
 
}