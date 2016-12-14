/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.springmvc.controllers;

import cz.fi.muni.pa165.api.dto.ApplicationRejectedRecordDTO;
import cz.fi.muni.pa165.api.facade.ApplicationRejectedRecordFacade;
import static cz.fi.muni.pa165.springmvc.controllers.CarController.log;
import java.util.UUID;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

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
     * 
     * @param id
     * @param model
     * @return 
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable UUID id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("record", rejectedFacade.findRecordById(id));
        return "rejected/view";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newRecord(Model model) {
        log.debug("new()");
        model.addAttribute("createRecord", new ApplicationRejectedRecordDTO());
        return "rejected/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("createRecord") ApplicationRejectedRecordDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(formBean={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "category/new";
        }
        //create product
        UUID id = rejectedFacade.create(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Category " + id + " was created");
        return "redirect:" + uriBuilder.path("/category/list").toUriString();
    }    
}
