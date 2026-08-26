package ecommerce.ecommerce.repository;

import ecommerce.ecommerce.entity.Promotion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Optional<Promotion> findByCode(String code);
    List<Promotion> findByActiveTrue();
}
