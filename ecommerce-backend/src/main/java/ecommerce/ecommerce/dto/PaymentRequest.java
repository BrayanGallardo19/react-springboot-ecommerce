package ecommerce.ecommerce.dto;

import ecommerce.ecommerce.enums.PaymentProvider;

public record PaymentRequest(
    Long orderId,
    PaymentProvider provider,
    Double amount,
    String externalReference
) {
}
