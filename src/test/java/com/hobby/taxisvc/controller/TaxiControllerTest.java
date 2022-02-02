package com.hobby.taxisvc.controller;

import com.hobby.taxisvc.controller.advise.GlobalAdvise;
import com.hobby.taxisvc.domain.Taxi;
import com.hobby.taxisvc.service.TaxiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class TaxiControllerTest  {

    @InjectMocks
    TaxiController taxiController;

    private MockMvc mockMvc;

    @Mock
    TaxiService service;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(taxiController).setControllerAdvice(new GlobalAdvise()).build();
    }

    @Test
    @DisplayName("operator should be able to register a taxi")
    void shouldRegisterATaxi() throws Exception {
        String requestBody = """
{"vehicleID":"vehicle-id-1","ownerName":"danish","capacity":4}""";
        var requestBuilder = MockMvcRequestBuilders.post("/v1/taxi/register")
                .contentType("application/json").content(requestBody);
        Taxi taxi = new Taxi("vehicle-id-1","danish",4);
        Mockito.when(service.register(taxi)).thenReturn(taxi);

        ResultActions result = mockMvc.perform(requestBuilder);

        var response = result
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(requestBody.trim(), response.trim());
    }

    @Test
    @DisplayName("operator should get an invalid data error message")
    void shouldFailForBadData() throws Exception {
        String requestBody = """
{"vehicleID":"","ownerName":"","capacity":0}""";
        String expectedResponse = """
                {"data":null,"errors":[{"msg":"capacity must be greater than 3 and less than 13, owner name must be greater than 4 and less than 255, the vehicle id must be greater than 4 and less than 11","code":"INVALID_DATA"}]}
                """;
        var requestBuilder = MockMvcRequestBuilders.post("/v1/taxi/register")
                .contentType("application/json").content(requestBody);

        ResultActions result = mockMvc.perform(requestBuilder);

        var response = result
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals(expectedResponse.trim(), response.trim());
    }
}