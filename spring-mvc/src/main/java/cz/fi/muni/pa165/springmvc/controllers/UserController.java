package cz.fi.muni.pa165.springmvc.controllers;

import cz.fi.muni.pa165.api.dto.UserAuthDTO;
import cz.fi.muni.pa165.api.dto.UserDTO;
import cz.fi.muni.pa165.api.facade.CarAuditLogItemFacade;
import cz.fi.muni.pa165.api.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
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

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("list()");
        model.addAttribute("users", userFacade.findAll());
        return "user/list";
    }

    @RequestMapping(value = "/view/{userId}", method = RequestMethod.GET)
    public String detail(@PathVariable UUID userId, Model model) {
        log.debug("detail({})", userId);
        UserDTO user = userFacade.findById(userId);
        if(user == null) {
            log.warn("No user with such id found");
            return list(model);
        }
        model.addAttribute("user", user);
        model.addAttribute("logItems", carAuditLogItemFacade.findByUser(userId));
        return "user/view";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password, ServletRequest r, Model model, Locale locale) {
        log.debug("login()");
        UserAuthDTO userAuthDTO = new UserAuthDTO();
        userAuthDTO.setEmail(email);
        userAuthDTO.setPassword(password);

        HttpServletRequest request = (HttpServletRequest) r;
        if (userFacade.auth(userAuthDTO)) {
            log.debug("user was authenticated");
            UserDTO user = userFacade.findByEmail(userAuthDTO.getEmail());
            HttpSession session = request.getSession();
            session.setAttribute("userAuth", user);
            model.addAttribute("success", message("success.login.welcome", locale, user.getFirstName()));
            model.addAttribute("user", user);
            return "home";
        } else {
            log.debug("user was NOT authenticated - wrong credentials");
            model.addAttribute("danger", message("danger.login.wrong", locale));
            return "login";
        }
    }

    private String message(String code, Locale locale, Object... args) {
        return messageSource.getMessage(code, args, locale);
    }
}
