package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.WebContext;
import cz.fi.muni.pa165.rest.ApiUris;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.testng.Assert.*;

/**
 * @author rtrembecky
 */
@WebAppConfiguration
@ContextConfiguration(classes = {WebContext.class})

public class MainControllerTest extends AbstractTestNGSpringContextTests {

    private final MockMvc mockMvc = standaloneSetup(new MainController()).build();

    @Test(enabled = false)
    public void mainControllerTest() throws Exception {

        mockMvc.perform(get("/"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("cars_uri").value(ApiUris.ROOT_URI_CARS))
                .andExpect(jsonPath("users_uri").value(ApiUris.ROOT_URI_USERS))
                .andExpect(jsonPath("records_uri").value(ApiUris.ROOT_URI_RECORDS))
                .andExpect(jsonPath("rejected_uri").value(ApiUris.ROOT_URI_REJECTED))
                .andExpect(jsonPath("approved_uri").value(ApiUris.ROOT_URI_APPROVED));
    }
}
