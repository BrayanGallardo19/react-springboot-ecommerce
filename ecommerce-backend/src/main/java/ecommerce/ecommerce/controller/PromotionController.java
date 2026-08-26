package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.dto.PromotionRequest;
import ecommerce.ecommerce.entity.Promotion;
import ecommerce.ecommerce.service.PromotionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping("/promotions")
    public ResponseEntity<List<Promotion>> getActivePromotions() {
        return ResponseEntity.ok(promotionService.getActivePromotions());
    }

    @PostMapping("/admin/promotions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Promotion> createPromotion(@RequestBody PromotionRequest request) {
        return ResponseEntity.ok(promotionService.create(request));
    }
}
