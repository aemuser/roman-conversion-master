package com.service.romanconversion.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/*
  Test Driven Development approach
   - This class 1) does Smoke tests (endpoints) test of the Roman numeral Conversion service
                2) validates the query params required criteria
                3) Validates the responses for various types of input ( valid and invalid)

*/

@SpringBootTest
@AutoConfigureMockMvc
public class RomanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String REQUEST_STRING = "/romannumeral?query=";

    @Test
    void test_RomanMaxValidationException() throws Exception {
        this.mockMvc.perform(get(REQUEST_STRING+"4000"))
                    .andExpect(status().isBadRequest());
    }

    @Test
    void test_RomanMinValidationException() throws Exception {
        this.mockMvc.perform(get(REQUEST_STRING + "0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_RomanSuccessValidation_1() throws Exception {
        this.mockMvc.perform(get(REQUEST_STRING+"1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("I")));

        this.mockMvc.perform(get(REQUEST_STRING +"12"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"input\":12,\"output\":\"XII\"}"));
    }

    @Test
    void test_RomanService_VariousInputs() throws Exception {
        this.mockMvc.perform(get(REQUEST_STRING))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(REQUEST_STRING+"abc"))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(REQUEST_STRING+"!@#!%!@$#"))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(REQUEST_STRING+"             "))
                .andExpect(status().isBadRequest());
    }


}
