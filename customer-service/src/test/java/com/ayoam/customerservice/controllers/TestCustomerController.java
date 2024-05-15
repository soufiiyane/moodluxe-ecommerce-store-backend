package com.ayoam.customerservice.controllers;

import com.ayoam.customerservice.controller.CustomerController;
import com.ayoam.customerservice.dto.CustomerDetailsDto;
import com.ayoam.customerservice.security.SecurityConfigTest;
import com.ayoam.customerservice.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import javax.ws.rs.core.MediaType;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(profiles = "test")
@WebMvcTest(CustomerController.class)
@Import(SecurityConfigTest.class)
public class TestCustomerController {
    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final String CONTROLLER_ENDPOINT = "/customers";

    @Nested
    class TestGetCustomerById {
        @Test
        void itShouldGetCustomerById() throws Exception {
            // Given
            Long customerID = 1L;
            // When
            ResultActions result = mockMvc.perform(get(CONTROLLER_ENDPOINT + "/" + customerID)
                    .contentType(MediaType.APPLICATION_JSON));
            // Then
            verify(customerService).getCustomerById(customerID);
            result.andExpect(status().isOk());
        }
    }

    @Nested
    class TestDeleteCustomer {
        @Test
        void itShouldInvokeServiceDeleteCustomer() throws Exception {
            // Given
            Long customerID = 1L;

            // When
            ResultActions result = mockMvc.perform(delete(CONTROLLER_ENDPOINT + "/" + customerID)
                    .contentType(MediaType.APPLICATION_JSON));
            // Then
            verify(customerService).deleteCustomer(customerID);
            result.andExpect(status().isOk());
        }
    }

    @Nested
    class TestUpdateCustomerDetails {
        @Test
        void itShouldInvokeServiceUpdateCustomerDetails() throws Exception {
            // Given
            CustomerDetailsDto customerDetailsDto = Instancio.create(CustomerDetailsDto.class);
            Long customerID = 1L;

            // When
            ResultActions result = mockMvc.perform(put(CONTROLLER_ENDPOINT + "/" + customerID + "/updateDetails")
                    .content(objectMapper.writeValueAsString(customerDetailsDto))
                    .contentType(MediaType.APPLICATION_JSON));
            // Then
            verify(customerService).updateCustomerDetails(customerDetailsDto,customerID);
            result.andExpect(status().isOk());
        }
    }
}
