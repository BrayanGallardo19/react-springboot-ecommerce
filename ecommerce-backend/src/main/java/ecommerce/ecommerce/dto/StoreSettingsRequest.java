package ecommerce.ecommerce.dto;

public record StoreSettingsRequest(
    String companyName,
    String phone,
    String email,
    String address,
    String footerLinks,
    String contactMessage
) {
}
