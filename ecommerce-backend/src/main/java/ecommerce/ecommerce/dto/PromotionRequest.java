package ecommerce.ecommerce.dto;

import java.time.LocalDateTime;

public record PromotionRequest(
    String name,
    String code,
    Double discountPercent,
    boolean active,
    LocalDateTime startsAt,
    LocalDateTime endsAt
) {
}
