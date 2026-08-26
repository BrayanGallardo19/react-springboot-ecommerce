package ecommerce.ecommerce.repository;

import ecommerce.ecommerce.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActiveTrueOrderByFeaturedDesc();
    List<Product> findByFeaturedTrueAndActiveTrue();
}
