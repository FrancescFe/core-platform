package com.inditex.challenge.coreplatform.prices.unittests.application;

import com.inditex.challenge.coreplatform.prices.application.PriceMapper;
import com.inditex.challenge.coreplatform.prices.application.PriceServiceImpl;
import com.inditex.challenge.coreplatform.prices.application.exceptions.PriceNotFoundException;
import com.inditex.challenge.coreplatform.prices.domain.Price;
import com.inditex.challenge.coreplatform.prices.infrastructure.controllers.DTO.GetPriceResponseDTO;
import com.inditex.challenge.coreplatform.prices.infrastructure.repositories.PriceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void shouldReturnCorrectPrice_WhenPriceExists() throws PriceNotFoundException {
        Integer brandId = 1;
        Integer productId = 35455;
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");

        Price price = new Price();
        price.setProductId(productId);
        price.setBrandId(brandId);
        price.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        price.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        price.setPrice(35.50);
        price.setPriority(0);

        GetPriceResponseDTO expectedResponse = new GetPriceResponseDTO(
                brandId, price.getStartDate(), price.getEndDate(), price.getPriceList(),
                productId, price.getPriority(), price.getPrice(), price.getCurrency());

        when(priceRepository.findTopByProductIdAndBrandAndDateRangeOrderByPriority(any(), any(), any()))
                .thenReturn(Optional.of(price));
        when(priceMapper.mapToGetPriceResponseDTO(price)).thenReturn(expectedResponse);

        GetPriceResponseDTO result = priceService.findPrice(brandId, productId, date);

        assertNotNull(result);
        assertEquals(expectedResponse, result);
    }

    @Test
    void shouldThrowPriceNotFoundException_WhenPriceDoesNotExist() {
        Integer brandId = 1;
        Integer productId = 35455;
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");

        when(priceRepository.findTopByProductIdAndBrandAndDateRangeOrderByPriority(any(), any(), any()))
                .thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () -> priceService.findPrice(brandId, productId, date));
    }
}
