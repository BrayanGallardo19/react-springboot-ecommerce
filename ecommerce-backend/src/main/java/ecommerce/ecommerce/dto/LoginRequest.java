package ecommerce.ecommerce.dto;

public record LoginRequest(
    String email,
    String password
) {
}
