package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.dto.ProductRequest;
import ecommerce.ecommerce.entity.Product;
import ecommerce.ecommerce.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping("/admin/products")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.create(request));
    }
}
