/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.springmvc.controllers;

import cz.fi.muni.pa165.api.facade.RentRecordFacade;
import cz.fi.muni.pa165.api.facade.ReturnRecordFacade;
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
@RequestMapping("/return")
public class ReturnRecordController {

    final static Logger log = LoggerFactory.getLogger(ReturnRecordController.class);


    @Autowired
    private ReturnRecordFacade returnFacade;

    /**
     * Shows a list of all return records.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("records", returnFacade.findAll());
        return "return/list";
    }
    
    /**
     * Show detail of return record with certain id
     * 
     * @param id is id of return record
     * @param model data to display
     * @return 
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable UUID id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("record", returnFacade.findById(id));
        return "return/view";
    }
 
}