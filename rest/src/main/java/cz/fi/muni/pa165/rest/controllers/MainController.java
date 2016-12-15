/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.rest.ApiUris;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    
    final static Logger logger = LoggerFactory.getLogger(MainController.class);
    
    /**
     * The main entry point of the REST API
     * Provides access to all the resources with links to resource URIs
     * Can be even extended further so that all the actions on all the resources are available
     * and can be reused in all the controllers (possibly in full HATEOAS style)
     * 
     * @return resources uris
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String,String> resourcesMap = new HashMap<>();
        
        resourcesMap.put("cars_uri", ApiUris.ROOT_URI_CARS);
        resourcesMap.put("users_uri", ApiUris.ROOT_URI_USERS);
        resourcesMap.put("records_uri", ApiUris.ROOT_URI_RECORDS);
        resourcesMap.put("rejected_uri", ApiUris.ROOT_URI_REJECTED);
        resourcesMap.put("approved_uri", ApiUris.ROOT_URI_APPROVED);

        return Collections.unmodifiableMap(resourcesMap);
        
    }
}
