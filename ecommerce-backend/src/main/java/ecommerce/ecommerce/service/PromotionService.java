package ecommerce.ecommerce.service;

import ecommerce.ecommerce.dto.PromotionRequest;
import ecommerce.ecommerce.entity.Promotion;
import ecommerce.ecommerce.repository.PromotionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public List<Promotion> getActivePromotions() {
        return promotionRepository.findByActiveTrue();
    }

    public Promotion create(PromotionRequest request) {
        Promotion promotion = new Promotion();
        promotion.setName(request.name());
        promotion.setCode(request.code());
        promotion.setDiscountPercent(request.discountPercent());
        promotion.setActive(request.active());
        promotion.setStartsAt(request.startsAt());
        promotion.setEndsAt(request.endsAt());
        return promotionRepository.save(promotion);
    }

    public Promotion findByCode(String code) {
        return promotionRepository.findByCode(code)
            .orElseThrow(() -> new IllegalArgumentException("Cupón no encontrado"));
    }
}
