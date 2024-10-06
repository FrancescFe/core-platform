package com.inditex.challenge.coreplatform.prices.infrastructure.controllers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GetPriceResponseDTO {
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceList;
    private Integer productId;
    private Integer priority;
    private Double price;
    private String currency;
}
