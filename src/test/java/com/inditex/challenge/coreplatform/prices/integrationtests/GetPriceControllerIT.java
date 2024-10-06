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

    private static final Integer BRAND_ID_VALUE = 1;
    private static final String DATE_TO_CHECK_VALUE = "2020-06-14T10:00:00";
    private static final Integer PRODUCT_ID_VALUE = 35455;
    private static final String PRICE_PATH = "/prices";
    private static final String BRAND_ID = "brandId";
    private static final String PRODUCT_ID = "productId";
    private static final String DATE = "date";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnCorrectPriceForGivenProductAndBrandAtSpecificDate() throws Exception {
        MvcResult result =
                mockMvc.perform(get(PRICE_PATH)
                                .param(BRAND_ID, BRAND_ID_VALUE.toString())
                                .param(PRODUCT_ID, PRODUCT_ID_VALUE.toString())
                                .param(DATE, DATE_TO_CHECK_VALUE)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        GetPriceResponseDTO response = objectMapper.readValue(jsonResponse, GetPriceResponseDTO.class);

        assertEquals(BRAND_ID_VALUE, response.getBrandId());
        assertEquals(PRODUCT_ID_VALUE, response.getProductId());
        assertEquals(1, response.getPriceList());
        assertEquals(35.50, response.getPrice());
    }

    @Test
    void shouldReturnBadRequest_WhenProductIdIsMissing() throws Exception {
        mockMvc.perform(get(PRICE_PATH)
                        .param(BRAND_ID, BRAND_ID_VALUE.toString())
                        .param(DATE, DATE_TO_CHECK_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequest_WhenDateIsMissing() throws Exception {
        mockMvc.perform(get(PRICE_PATH)
                        .param(BRAND_ID, BRAND_ID_VALUE.toString())
                        .param(PRODUCT_ID, PRODUCT_ID_VALUE.toString()))
                .andExpect(status().isBadRequest());
    }
}
