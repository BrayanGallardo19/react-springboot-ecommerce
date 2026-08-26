package ecommerce.ecommerce.dto;

import java.util.List;

public record OrderRequest(
    List<OrderItemRequest> items,
    String couponCode
) {
    public record OrderItemRequest(
        Long productId,
        Integer quantity
    ) {
    }
}
