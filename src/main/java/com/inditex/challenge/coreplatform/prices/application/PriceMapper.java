package com.inditex.challenge.coreplatform.prices.application;

import com.inditex.challenge.coreplatform.prices.domain.Price;
import com.inditex.challenge.coreplatform.prices.infrastructure.controllers.DTO.GetPriceResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    GetPriceResponseDTO mapToGetPriceResponseDTO(Price price);
}
