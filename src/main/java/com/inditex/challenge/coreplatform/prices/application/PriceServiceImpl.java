package com.inditex.challenge.coreplatform.prices.application;

import com.inditex.challenge.coreplatform.prices.application.exceptions.PriceNotFoundException;
import com.inditex.challenge.coreplatform.prices.domain.Price;
import com.inditex.challenge.coreplatform.prices.infrastructure.controllers.DTO.GetPriceResponseDTO;
import com.inditex.challenge.coreplatform.prices.infrastructure.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public GetPriceResponseDTO findPrice(Integer brandId, Integer productId, LocalDateTime date)
            throws PriceNotFoundException {
        Price highestPriorityPrice = priceRepository
                .findTopByProductIdAndBrandAndDateRangeOrderByPriority(brandId, productId, date)
                .orElseThrow(() -> new PriceNotFoundException(brandId, productId, date));

        return priceMapper.mapToGetPriceResponseDTO(highestPriorityPrice);
    }
}
