package ecommerce.ecommerce.dto;

public record ProductRequest(
    String name,
    String description,
    Double price,
    Integer stock,
    Long categoryId,
    String imageUrl,
    boolean active,
    boolean featured,
    Double discountPercent
) {
}
