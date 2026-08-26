package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.dto.StoreSettingsRequest;
import ecommerce.ecommerce.entity.StoreSetting;
import ecommerce.ecommerce.repository.StoreSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final StoreSettingRepository storeSettingRepository;

    @GetMapping("/store-settings")
    public ResponseEntity<StoreSetting> getStoreSettings() {
        StoreSetting storeSetting = storeSettingRepository.findTopByOrderByIdAsc()
            .orElseGet(() -> storeSettingRepository.save(new StoreSetting()));
        return ResponseEntity.ok(storeSetting);
    }

    @PutMapping("/store-settings")
    public ResponseEntity<StoreSetting> updateStoreSettings(@RequestBody StoreSettingsRequest request) {
        StoreSetting storeSetting = storeSettingRepository.findTopByOrderByIdAsc()
            .orElseGet(() -> storeSettingRepository.save(new StoreSetting()));

        storeSetting.setCompanyName(request.companyName() != null ? request.companyName() : storeSetting.getCompanyName());
        storeSetting.setPhone(request.phone() != null ? request.phone() : storeSetting.getPhone());
        storeSetting.setEmail(request.email() != null ? request.email() : storeSetting.getEmail());
        storeSetting.setAddress(request.address() != null ? request.address() : storeSetting.getAddress());
        storeSetting.setFooterLinks(request.footerLinks() != null ? request.footerLinks() : storeSetting.getFooterLinks());
        storeSetting.setContactMessage(request.contactMessage() != null ? request.contactMessage() : storeSetting.getContactMessage());

        return ResponseEntity.ok(storeSettingRepository.save(storeSetting));
    }
}
