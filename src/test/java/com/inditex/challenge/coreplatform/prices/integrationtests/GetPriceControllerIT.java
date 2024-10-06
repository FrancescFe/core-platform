package com.inditex.challenge.coreplatform.prices.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.challenge.coreplatform.prices.infrastructure.controllers.DTO.GetPriceResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetPriceControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnCorrectPriceForGivenProductAndBrandAtSpecificDate() throws Exception {
        Integer brandId = 1;
        Integer productId = 35455;
        String date = "2020-06-14T10:00:00";

        MvcResult result =
                mockMvc.perform(get("/prices")
                                .param("brandId", brandId.toString())
                                .param("productId", productId.toString())
                                .param("date", date)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        GetPriceResponseDTO response = objectMapper.readValue(jsonResponse, GetPriceResponseDTO.class);

        assertEquals(brandId, response.getBrandId());
        assertEquals(productId, response.getProductId());
        assertEquals(1, response.getPriceList());
        assertEquals(35.50, response.getPrice());
    }

    @Test
    void shouldReturnBadRequest_WhenProductIdIsMissing() throws Exception {
        Integer brandId = 1;
        String date = "2020-06-14T10:00:00";

        mockMvc.perform(get("/prices")
                        .param("brandId", brandId.toString())
                        .param("date", date))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequest_WhenDateIsMissing() throws Exception {
        Integer brandId = 1;
        Integer productId = 35455;

        mockMvc.perform(get("/prices")
                        .param("brandId", brandId.toString())
                        .param("productId", productId.toString()))
                .andExpect(status().isBadRequest());
    }
}
