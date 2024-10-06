package com.inditex.challenge.coreplatform.prices.application;

import com.inditex.challenge.coreplatform.prices.application.exceptions.PriceNotFoundException;
import com.inditex.challenge.coreplatform.prices.infrastructure.controllers.DTO.GetPriceResponseDTO;

import java.time.LocalDateTime;

public interface PriceService {
    GetPriceResponseDTO findPrice(Integer brandId, Integer productId, LocalDateTime date)
            throws PriceNotFoundException;

}
