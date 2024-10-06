package com.inditex.challenge.coreplatform.prices.infrastructure.controllers;

import com.inditex.challenge.coreplatform.prices.application.PriceService;
import com.inditex.challenge.coreplatform.prices.application.exceptions.PriceNotFoundException;
import com.inditex.challenge.coreplatform.prices.infrastructure.controllers.DTO.GetPriceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class GetPriceController {

    private final PriceService priceService;

    @Autowired
    public GetPriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/prices")
    public ResponseEntity<GetPriceResponseDTO> getPrice(
            @RequestParam("productId") Integer productId,
            @RequestParam("brandId") Integer brandId,
            @RequestParam("date") LocalDateTime date
    ) throws PriceNotFoundException {
        return ResponseEntity.ok(priceService
                .findPrice(brandId, productId, date));
    }
}
