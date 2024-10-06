package com.inditex.challenge.coreplatform.prices.qa;

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
public class PriceQaTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final Integer PRODUCT_ID = 35455;
    private final Integer BRAND_ID = 1;

    @Test
    void testCase1() throws Exception {
        String date = "2020-06-14T10:00:00";
        performTest(date, 35.50);  // expected price value
    }

    @Test
    void testCase2() throws Exception {
        String date = "2020-06-14T16:00:00";
        performTest(date, 25.45);  // expected price value
    }

    @Test
    void testCase3() throws Exception {
        String date = "2020-06-14T21:00:00";
        performTest(date, 35.50);  // expected price value
    }

    @Test
    void testCase4() throws Exception {
        String date = "2020-06-15T10:00:00";
        performTest(date, 30.50);  // expected price value
    }

    @Test
    void testCase5() throws Exception {
        String date = "2020-06-16T21:00:00";
        performTest(date, 38.95);  // expected price value
    }

    private void performTest(String date, double expectedPrice) throws Exception {
        MvcResult result = mockMvc.perform(get("/prices")
                        .param("brandId", BRAND_ID.toString())
                        .param("productId", PRODUCT_ID.toString())
                        .param("date", date)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        GetPriceResponseDTO response = objectMapper.readValue(jsonResponse, GetPriceResponseDTO.class);

        assertEquals(BRAND_ID, response.getBrandId());
        assertEquals(PRODUCT_ID, response.getProductId());
        assertEquals(expectedPrice, response.getPrice());
    }
}
