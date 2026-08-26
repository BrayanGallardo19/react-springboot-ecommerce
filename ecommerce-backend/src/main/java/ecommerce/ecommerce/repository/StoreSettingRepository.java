package ecommerce.ecommerce.repository;

import ecommerce.ecommerce.entity.StoreSetting;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreSettingRepository extends JpaRepository<StoreSetting, Long> {
    Optional<StoreSetting> findTopByOrderByIdAsc();
}
