/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.springmvc.controllers;


import cz.fi.muni.pa165.api.dto.*;
import cz.fi.muni.pa165.api.facade.CarAuditLogItemFacade;
import cz.fi.muni.pa165.api.facade.RentApplicationFacade;
import cz.fi.muni.pa165.api.facade.UserFacade;
import cz.fi.muni.pa165.model.CarAuditLogItemType;
import org.apache.commons.lang.ArrayUtils;
import org.apache.derby.iapi.services.io.ArrayUtil;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public String create(
            @PathVariable("carId") UUID carId,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder
    ) {

        CarLogStateDTO logState = carAuditLogItemFacade.findLogState(carId);

        model.addAttribute("carId", carId);
        final List<CarLogPossibleStateDTO> possibleStates;
        if(logState != null) {
            model.addAttribute("currentState", logState.getTypeName());
            model.addAttribute("currentRecordId", logState.getRecordId());
            possibleStates = logState.getPossibleStates();
        } else {
            model.addAttribute("currentState", null);
            model.addAttribute("currentRecordId", null);
            possibleStates = carAuditLogItemFacade.getInitialStates();
        }

        for (CarLogPossibleStateDTO dto : possibleStates) {
            // todo: this contains potential XSS vulnerability; should be escaped
            String url = "/records/add/" + carId + "/" + dto.getId();
            if(logState != null) {
                url += "?lastRecordId=" + logState.getRecordId();
            }
            dto.setUrl(url);
        }
        model.addAttribute("possibleNextSteps", possibleStates);

        return "records/select";
    }

    @RequestMapping(value = "/add/{carId}/{recordType}", method = RequestMethod.GET)
    public String create(
            @PathVariable("carId") UUID carId,
            @PathVariable(value = "recordType") String recordType,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder,
            @RequestParam(value = "lastRecordId", required = false) String lastRecordId
    ) {
        if(!carAuditLogItemFacade.isRecordTypeValid(recordType)) {
            throw new RuntimeException("Invalid record type");
        }

        model.addAttribute("formSubmitUrl", "/records/create/rentApplication?carId=" + carId + "&lastRecordId=" + lastRecordId);


        switch(recordType) {
            case "rentApplication":
                model.addAttribute("recordDTO", new RentApplicationDTO());
                break;

            default:
                throw new RuntimeException("Wrong record type");
        }

        return "records/" + recordType;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("dd. MM. yyyy"), true, 12));
    }

    @Autowired
    private UserFacade userFacade;

    private UUID getSomeUserId() {
        // todo: remove when login is finished
        final List<UserDTO> all = userFacade.findAll();
        final UserDTO userDTO = all.get(all.size() - 1);
        return userDTO.getId();
    }

    @Autowired
    private RentApplicationFacade rentApplicationFacade;

    @RequestMapping(value = "/create/rentApplication", method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("recordDTO") RentApplicationDTO recordDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder,
            @RequestParam("carId") UUID carId
    ) {
        log.debug("create(formBean={})", recordDTO);

        String[] validatedFields = new String[] { "from", "to", "comment" };

        //in case of validation error forward back to the the form
        boolean errors = false;
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                if(ArrayUtils.indexOf(validatedFields, fe.getField()) == -1) continue;

                errors = true;
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
        }

        if(errors) {
            return "records/rentApplication";
        }

        final UserDTO userDTO = new UserDTO();
        userDTO.setId( getSomeUserId() );
        recordDTO.setUser(userDTO);

        final CarDTO carDTO = new CarDTO();
        carDTO.setId( carId );
        recordDTO.setCar(carDTO);

        recordDTO.setCreated(new Date());

        UUID id = rentApplicationFacade.create(recordDTO);

        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Rent application with ID " + id + " was created");
        return "redirect:" + uriBuilder.path("/records/list").toUriString();
    }

}
