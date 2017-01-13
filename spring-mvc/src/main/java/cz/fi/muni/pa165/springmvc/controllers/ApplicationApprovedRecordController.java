package cz.fi.muni.pa165.springmvc.controllers;

import cz.fi.muni.pa165.api.facade.ApplicationApprovedRecordFacade;
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
 * @author rtrembecky
 */
@Controller
@RequestMapping("/approved")
public class ApplicationApprovedRecordController {

    final static Logger log = LoggerFactory.getLogger(ApplicationRejectedRecordController.class);

    @Autowired
    private ApplicationApprovedRecordFacade approvedFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("records", approvedFacade.findAllRecords());
        return "approved/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable UUID id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("record", approvedFacade.findRecordById(id));
        return "approved/view";
    }
}
