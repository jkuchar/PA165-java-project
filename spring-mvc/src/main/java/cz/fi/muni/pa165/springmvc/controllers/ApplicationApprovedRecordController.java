package cz.fi.muni.pa165.springmvc.controllers;

import cz.fi.muni.pa165.api.dto.ApplicationApprovedRecordDTO;
import cz.fi.muni.pa165.api.facade.ApplicationApprovedRecordFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author rtrembecky
 */
@Controller
@RequestMapping("/approved")
public class ApplicationApprovedRecordController {

    final static Logger log = LoggerFactory.getLogger(ApplicationApprovedRecordController.class);

    @Autowired
    private ApplicationApprovedRecordFacade approvedFacade;

    /**
     * Shows a list of user's application approved records.
     *
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "redirect:list/my";
    }

    /**
     * Shows a list of application approved records based on filter.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list/{filter}", method = RequestMethod.GET)
    public String list(@PathVariable String filter, Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();

        List<ApplicationApprovedRecordDTO> records;
        if (filter.equals("all"))
            if (request.isUserInRole("ROLE_MANAGER"))
                records = approvedFacade.findAllRecords();
            else {
                records = new ArrayList<>();
                model.addAttribute("warning", "You don't have permission to view all records.");
            }
        else if (filter.equals("my"))
            records = approvedFacade.findAllRecordsByUserEmail(user.getUsername());
        else {
            records = new ArrayList<>();
            model.addAttribute("danger", "Unknown filter " + filter);
        }
        model.addAttribute("records", records);
        return "approved/list";
    }

    /**
     * Show detail of approved record with certain id
     *
     * @param id is id of approved record
     * @param model data to display
     * @return
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable UUID id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("record", approvedFacade.findRecordById(id));
        return "approved/view";
    }
}
