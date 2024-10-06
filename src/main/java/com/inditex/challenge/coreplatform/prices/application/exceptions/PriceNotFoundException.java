package com.inditex.challenge.coreplatform.prices.application.exceptions;

import com.inditex.challenge.coreplatform.shared.exceptions.ErrorDetails;
import com.inditex.challenge.coreplatform.shared.exceptions.ErrorMessages;

import java.time.LocalDateTime;

public class PriceNotFoundException extends ErrorDetails {
    public PriceNotFoundException(Integer brandId, Integer productId, LocalDateTime date) {
        super(String.format(ErrorMessages.PriceNotFound.MESSAGE, brandId, productId, date),
                ErrorMessages.PriceNotFound.CODE);
    }
}
