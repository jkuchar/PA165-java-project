/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.springmvc.controllers;

import cz.fi.muni.pa165.api.dto.*;
import cz.fi.muni.pa165.api.facade.*;
import cz.fi.muni.pa165.model.CarAuditLogItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
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

        addFormSubmitUrl(carId, recordType, model, lastRecordId);

        switch(recordType) {
            case "rentApplication":
                model.addAttribute("recordDTO", new RentApplicationDTO());
                break;

            case "applicationApproved":
                model.addAttribute("recordDTO", new ApplicationApprovedRecordDTO());
                break;

            case "applicationRejected":
                model.addAttribute("recordDTO", new ApplicationRejectedRecordDTO());
                break;

            case "returnRecord":
                model.addAttribute("recordDTO", new ReturnRecordDTO());
                break;

            case "rentRecord":
                model.addAttribute("recordDTO", new RentRecordDTO());
                break;

            default:
                throw new RuntimeException("Wrong record type");
        }

        return "records/" + recordType;
    }
    
    @RequestMapping(value = "/view/{logItemId}", method = RequestMethod.GET)
    public String detail(        
            @PathVariable UUID logItemId,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder
    ) {
        
        CarAuditLogItemDTO log =  carAuditLogItemFacade.findById(logItemId);

        switch(log.getType()) {
            case "Rent application":
                return "redirect:" + uriBuilder.path("/application/view/{logItemId}").buildAndExpand(logItemId).encode().toUriString();
 
            case "Application approved record":
                return "redirect:" + uriBuilder.path("/approved/view/{logItemId}").buildAndExpand(logItemId).encode().toUriString();
            
            case "Application rejected record":
                return "redirect:" + uriBuilder.path("/rejected/view/{logItemId}").buildAndExpand(logItemId).encode().toUriString();

            case "Return record":
                return "redirect:" + uriBuilder.path("/return/view/{logItemId}").buildAndExpand(logItemId).encode().toUriString();

            case "Rent record":
                return "redirect:" + uriBuilder.path("/rent/view/{logItemId}").buildAndExpand(logItemId).encode().toUriString();

            default:
                throw new RuntimeException("Wrong record type");
        }

    }

    private void addFormSubmitUrl(UUID carId, String recordType, Model model, String lastRecordId) {
        model.addAttribute("formSubmitUrl", "/records/create/" + recordType + "?carId=" + carId + "&lastRecordId=" + lastRecordId);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("dd. MM. yyyy"), true, 12));
    }

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private RentApplicationFacade rentApplicationFacade;

    @RequestMapping(value = "/create/rentApplication", method = RequestMethod.POST)
    public String createRentApplication(
            @Valid @ModelAttribute("recordDTO") RentApplicationDTO recordDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder,
            @RequestParam("carId") UUID carId
    ) {
        log.debug("create(formBean={})", recordDTO);

        addFormSubmitUrl(carId, CarAuditLogItemType.RENT_APPLICATION.getId(), model, "");
        //in case of validation error forward back to the the form

        if(validateRequestAndModel(
                bindingResult,
                model,
                new String[] { "from", "to", "comment" }
        )) {
            return "records/rentApplication";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        final UserDTO userDTO = userFacade.findByEmail(user.getUsername());
        recordDTO.setUser(userDTO);

        final CarDTO carDTO = new CarDTO();
        carDTO.setId( carId );
        recordDTO.setCar(carDTO);

        recordDTO.setCreated(new Date());

        UUID id = rentApplicationFacade.create(recordDTO);

        //report success
        redirectAttributes.addFlashAttribute("success", "Rent application with ID " + id + " was created");
        return "redirect:" + uriBuilder.path("/car/list/all").toUriString(); // todo better URI
    }

    @Autowired
    private ApplicationRejectedRecordFacade applicationRejectedRecordFacade;

    @RequestMapping(value = "/create/applicationRejected", method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("recordDTO") ApplicationRejectedRecordDTO recordDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder,
            @RequestParam("carId") UUID carId,
            @RequestParam("lastRecordId") UUID lastRecordId
    ) {
        log.debug("create(formBean={})", recordDTO);
        addFormSubmitUrl(carId, CarAuditLogItemType.APPLICATION_REJECTED.getId(), model, lastRecordId.toString());
        //in case of validation error forward back to the the form

        if(validateRequestAndModel(
                bindingResult,
                model,
                new String[] { "comment" }
        )) {
            return "records/rentApplication";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        final UserDTO userDTO = userFacade.findByEmail(user.getUsername());
        recordDTO.setUser(userDTO);

        final CarDTO carDTO = new CarDTO();
        carDTO.setId( carId );
        recordDTO.setCar(carDTO);

        recordDTO.setCreated(new Date());

        final RentApplicationDTO rentApplicationDTO = new RentApplicationDTO();
        rentApplicationDTO.setId(lastRecordId);
        recordDTO.setRentApplication(rentApplicationDTO);

        UUID id = applicationRejectedRecordFacade.create(recordDTO);

        //report success
        redirectAttributes.addFlashAttribute("success", "Application rejected record with ID " + id + " was created");
        return "redirect:" + uriBuilder.path("/car/list/all").toUriString(); // todo better URI
    }

    @Autowired
    private ApplicationApprovedRecordFacade applicationApprovedRecordFacade;
    
    @RequestMapping(value = "/create/applicationApproved", method = RequestMethod.POST)
    public String createApproved(
            @Valid @ModelAttribute("recordDTO") ApplicationApprovedRecordDTO recordDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder,
            @RequestParam("carId") UUID carId,
            @RequestParam("lastRecordId") UUID lastRecordId
    ) {
        log.debug("create(formBean={})", recordDTO);
        addFormSubmitUrl(carId, CarAuditLogItemType.APPLICATION_APPROVED.getId(), model, lastRecordId.toString());

        //in case of validation error forward back to the the form

        if(validateRequestAndModel(
                bindingResult,
                model,
                new String[] { "comment" }
        )) {
            return "records/applicationApproved";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        final UserDTO userDTO = userFacade.findByEmail(user.getUsername());
        recordDTO.setUser(userDTO);

        final CarDTO carDTO = new CarDTO();
        carDTO.setId( carId );
        recordDTO.setCar(carDTO);

        recordDTO.setCreated(new Date());

        recordDTO.setFrom(new Date()); // todo: add to form!
        recordDTO.setTo(new Date()); // todo: add to form!

        final RentApplicationDTO rentApplicationDTO = new RentApplicationDTO();
        rentApplicationDTO.setId(lastRecordId);
        recordDTO.setRentApplication(rentApplicationDTO);

        UUID id = applicationApprovedRecordFacade.create(recordDTO);

        //report success
        redirectAttributes.addFlashAttribute("success", "Application approved record with ID " + id + " was created");
        return "redirect:" + uriBuilder.path("/car/list/all").toUriString(); // todo better URI
    }

    @Autowired
    private ReturnRecordFacade returnRecordFacade;

    @RequestMapping(value = "/create/returnRecord", method = RequestMethod.POST)
    public String createReturnRecord(
            @Valid @ModelAttribute("recordDTO") ReturnRecordDTO recordDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder,
            @RequestParam("carId") UUID carId,
            @RequestParam("lastRecordId") UUID lastRecordId
    ) {
        log.debug("create(formBean={})", recordDTO);
        addFormSubmitUrl(carId, CarAuditLogItemType.RETURN_RECORD.getId(), model, lastRecordId.toString());

        //in case of validation error forward back to the the form

        if(validateRequestAndModel(
                bindingResult,
                model,
                new String[] { "comment", "fuelState", "odometerState" }
        )) {
            return "records/returnRecord";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        final UserDTO userDTO = userFacade.findByEmail(user.getUsername());
        recordDTO.setUser(userDTO);

        final CarDTO carDTO = new CarDTO();
        carDTO.setId( carId );
        recordDTO.setCar(carDTO);

        recordDTO.setCreated(new Date());

        final RentRecordDTO rentRecordDTO = new RentRecordDTO();
        rentRecordDTO.setId(lastRecordId);
        recordDTO.setRentRecord(rentRecordDTO);

        UUID id = returnRecordFacade.create(recordDTO);

        //report success
        redirectAttributes.addFlashAttribute("success", "Return record with ID " + id + " was created");
        return "redirect:" + uriBuilder.path("/car/list/all").toUriString(); // todo better URI
    }
    
    @Autowired
    private RentRecordFacade rentRecordFacade;

    @RequestMapping(value = "/create/rentRecord", method = RequestMethod.POST)
    public String createRentRecord(
            @Valid @ModelAttribute("recordDTO") RentRecordDTO recordDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder,
            @RequestParam("carId") UUID carId,
            @RequestParam("lastRecordId") UUID lastRecordId
    ) {
        log.debug("create(formBean={})", recordDTO);
        addFormSubmitUrl(carId, CarAuditLogItemType.RENT_RECORD.getId(), model, lastRecordId.toString());
        //in case of validation error forward back to the the form

        if(validateRequestAndModel(
                bindingResult,
                model,
                new String[] { "comment" }
        )) {
            return "records/rentRecord";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        final UserDTO userDTO = userFacade.findByEmail(user.getUsername());
        recordDTO.setUser(userDTO);

        final CarDTO carDTO = new CarDTO();
        carDTO.setId( carId );
        recordDTO.setCar(carDTO);

        recordDTO.setCreated(new Date());

        final ApplicationApprovedRecordDTO approvedRecordDTO = new ApplicationApprovedRecordDTO();
        approvedRecordDTO.setId(lastRecordId);
        recordDTO.setApprovedRecord(approvedRecordDTO);

        UUID id = rentRecordFacade.create(recordDTO);

        //report success
        redirectAttributes.addFlashAttribute("success", "Rent record with ID " + id + " was created");
        return "redirect:" + uriBuilder.path("/car/list/all").toUriString(); // todo better URI
    }

    private static boolean validateRequestAndModel(BindingResult bindingResult, Model model, String[] validatedFields) {
        return ValidationTools.validateRequestAndModel(bindingResult, model, validatedFields, log);
    }

}
