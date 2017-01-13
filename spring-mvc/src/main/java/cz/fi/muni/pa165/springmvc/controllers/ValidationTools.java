package cz.fi.muni.pa165.springmvc.controllers;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * This file is part of PA165 school project.
 */
class ValidationTools {

    static boolean validateRequestAndModel(BindingResult bindingResult, Model model, String[] validatedFields, Logger log) {
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
        return errors;
    }

}
