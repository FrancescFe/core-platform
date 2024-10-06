package com.inditex.challenge.coreplatform.shared.exceptions;

public class ErrorMessages {

    // 400
    public static final class PriceNotFound {
        public static final String MESSAGE =
                "Price not found for brandId: %d, productId: %d, on date: %s";
        public static final int CODE = 404;
    }

    // 500
}
