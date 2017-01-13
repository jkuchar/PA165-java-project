/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.springmvc.controllers;

import cz.fi.muni.pa165.api.facade.ApplicationRejectedRecordFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

/**
 *
 * @author charlliz
 */
@Controller
@RequestMapping("/rejected")
public class ApplicationRejectedRecordController {

    final static Logger log = LoggerFactory.getLogger(ApplicationRejectedRecordController.class);

    @Autowired
    private ApplicationRejectedRecordFacade rejectedFacade;

    /**
     * Shows a list of all application rejected records.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("records", rejectedFacade.findAllRecords());
        return "rejected/list";
    }
    
    /**
     * Show detail of rejected record with certain id
     *
     * @param id is id of rejected record
     * @param model data to display
     * @return 
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable UUID id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("record", rejectedFacade.findRecordById(id));
        return "rejected/view";
    }
}
