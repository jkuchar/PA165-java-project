package cz.fi.muni.pa165.springmvc.controllers;

import cz.fi.muni.pa165.api.dto.CarAuditLogItemDTO;
import cz.fi.muni.pa165.api.dto.UserDTO;
import cz.fi.muni.pa165.api.facade.CarAuditLogItemFacade;
import cz.fi.muni.pa165.api.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.UUID;

/**
 * @author rtrembecky
 */
@Controller
@RequestMapping("/user")
public class UserController {

    final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private CarAuditLogItemFacade carAuditLogItemFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("list()");
        model.addAttribute("users", userFacade.findAll());
        return "user/list";
    }

    @RequestMapping(value = "/view/{userId}", method = RequestMethod.GET)
    public String detail(@PathVariable UUID userId, Model model) {
        UserDTO user = userFacade.findById(userId);
        if(user == null) {
            log.warn("No user with such id found");
            return list(model);
        }
        model.addAttribute("user", user);
        model.addAttribute("logItems", carAuditLogItemFacade.findByUser(userId));
        return "user/view";
    }
}
