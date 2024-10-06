package com.inditex.challenge.coreplatform.prices.unittests.application;

import com.inditex.challenge.coreplatform.prices.application.PriceMapper;
import com.inditex.challenge.coreplatform.prices.application.PriceMapperImpl;
import com.inditex.challenge.coreplatform.prices.application.PriceServiceImpl;
import com.inditex.challenge.coreplatform.prices.application.exceptions.PriceNotFoundException;
import com.inditex.challenge.coreplatform.prices.domain.Price;
import com.inditex.challenge.coreplatform.prices.infrastructure.controllers.DTO.GetPriceResponseDTO;
import com.inditex.challenge.coreplatform.prices.infrastructure.repositories.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceImplTest {

    private static final Integer BRAND_ID_VALUE = 1;
    private static final Integer PRODUCT_ID_VALUE = 35455;
    private static final LocalDateTime DATE_TO_CHECK_VALUE = LocalDateTime.parse("2020-06-14T10:00:00");

    @Mock
    private PriceRepository priceRepository;

    @Spy
    private PriceMapper priceMapper = new PriceMapperImpl();

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void shouldReturnCorrectPrice_WhenPriceExists() throws PriceNotFoundException {

        Price price = new Price();
        price.setProductId(PRODUCT_ID_VALUE);
        price.setBrandId(BRAND_ID_VALUE);
        price.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        price.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        price.setPrice(35.50);
        price.setPriority(0);

        GetPriceResponseDTO expectedResponse = new GetPriceResponseDTO(
                BRAND_ID_VALUE, price.getStartDate(), price.getEndDate(), price.getPriceList(),
                PRODUCT_ID_VALUE, price.getPriority(), price.getPrice(), price.getCurrency());

        mockPriceRepository(Optional.of(price));

        GetPriceResponseDTO result = priceService.findPrice(BRAND_ID_VALUE, PRODUCT_ID_VALUE, DATE_TO_CHECK_VALUE);

        assertNotNull(result);
        assertEquals(expectedResponse, result);
    }

    @Test
    void shouldThrowPriceNotFoundException_WhenPriceDoesNotExist() {

        mockPriceRepository(Optional.empty());

        assertThrows(PriceNotFoundException.class, () -> priceService.findPrice(BRAND_ID_VALUE, PRODUCT_ID_VALUE, DATE_TO_CHECK_VALUE));
    }

    private void mockPriceRepository(Optional<Price> price) {
        when(priceRepository.findTopByProductIdAndBrandAndDateRangeOrderByPriority(any(), any(), any()))
                .thenReturn(price);
    }
}
