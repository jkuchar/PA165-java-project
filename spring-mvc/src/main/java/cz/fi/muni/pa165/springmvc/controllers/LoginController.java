package cz.fi.muni.pa165.springmvc.controllers;

import cz.fi.muni.pa165.api.dto.UserAuthDTO;
import cz.fi.muni.pa165.api.dto.UserDTO;
import cz.fi.muni.pa165.api.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * @author rtrembecky
 */
@Controller
public class LoginController {

    final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password, ServletRequest r, Model model, Locale locale) {
        log.debug("login()");
        UserAuthDTO userAuthDTO = new UserAuthDTO();
        userAuthDTO.setEmail(email);
        userAuthDTO.setPassword(password);

        HttpServletRequest request = (HttpServletRequest) r;
        if (userFacade.validate(userAuthDTO)) {
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

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, Model model, Locale locale) {
        log.debug("user logged out");
        HttpSession session = request.getSession();
        session.removeAttribute("userAuth");
        model.addAttribute("success", message("success.logout", locale));
        return "login";
    }

    private String message(String code, Locale locale, Object... args) {
        return messageSource.getMessage(code, args, locale);
    }
}
