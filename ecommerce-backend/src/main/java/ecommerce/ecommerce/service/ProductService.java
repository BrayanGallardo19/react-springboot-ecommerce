package ecommerce.ecommerce.service;

import ecommerce.ecommerce.dto.ProductRequest;
import ecommerce.ecommerce.entity.Category;
import ecommerce.ecommerce.entity.Product;
import ecommerce.ecommerce.repository.CategoryRepository;
import ecommerce.ecommerce.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> findAll() {
        return productRepository.findByActiveTrueOrderByFeaturedDesc();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    }

    public Product create(ProductRequest request) {
        Category category = categoryRepository.findById(request.categoryId())
            .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setCategory(category);
        product.setImageUrl(request.imageUrl());
        product.setActive(request.active());
        product.setFeatured(request.featured());
        product.setDiscountPercent(request.discountPercent() == null ? 0.0 : request.discountPercent());

        return productRepository.save(product);
    }
}
