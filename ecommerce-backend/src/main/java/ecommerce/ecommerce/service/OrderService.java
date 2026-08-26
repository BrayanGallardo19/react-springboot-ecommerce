package ecommerce.ecommerce.service;

import ecommerce.ecommerce.dto.OrderRequest;
import ecommerce.ecommerce.entity.Order;
import ecommerce.ecommerce.entity.OrderItem;
import ecommerce.ecommerce.entity.Product;
import ecommerce.ecommerce.entity.Promotion;
import ecommerce.ecommerce.entity.User;
import ecommerce.ecommerce.enums.OrderStatus;
import ecommerce.ecommerce.repository.OrderRepository;
import ecommerce.ecommerce.repository.ProductRepository;
import ecommerce.ecommerce.repository.PromotionRepository;
import ecommerce.ecommerce.repository.UserRepository;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PromotionRepository promotionRepository;

    public Order createOrder(Authentication authentication, OrderRequest request) {
        User user = userRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setItems(new ArrayList<>());

        double total = 0.0;
        for (OrderRequest.OrderItemRequest itemRequest : request.items()) {
            Product product = productRepository.findById(itemRequest.productId())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

            if (product.getStock() < itemRequest.quantity()) {
                throw new IllegalArgumentException("Stock insuficiente para " + product.getName());
            }

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemRequest.quantity());
            item.setUnitPrice(product.getPrice());
            order.getItems().add(item);
            total += product.getPrice() * itemRequest.quantity();

            // decrement stock
            product.setStock(product.getStock() - itemRequest.quantity());
            productRepository.save(product);
        }

        if (request.couponCode() != null && !request.couponCode().isBlank()) {
            Promotion promotion = promotionRepository.findByCode(request.couponCode())
                .orElseThrow(() -> new IllegalArgumentException("Cupón no válido"));
            if (promotion.isActive()) {
                total = total * (1 - promotion.getDiscountPercent() / 100.0);
            }
        }

        order.setTotal(total);
        Order saved = orderRepository.save(order);
        return saved;
    }

    public List<Order> findByCurrentUser(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        return orderRepository.findByUser(user);
    }

    /**
     * Generate a simple JSON receipt (boleta) for an order. Placeholder for real fiscal integration.
     */
    public Map<String, Object> generateReceipt(Authentication authentication, Long orderId) {
        User user = userRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("No autorizado para ver este comprobante");
        }

        Map<String, Object> receipt = new HashMap<>();
        receipt.put("boletaNumber", "BOLETA-" + order.getId());
        receipt.put("date", order.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME));
        receipt.put("client", Map.of("id", user.getId(), "email", user.getEmail(), "name", user.getFullName()));
        receipt.put("total", order.getTotal());

        List<Map<String, Object>> items = new ArrayList<>();
        for (OrderItem it : order.getItems()) {
            Map<String, Object> m = new HashMap<>();
            m.put("productId", it.getProduct().getId());
            m.put("name", it.getProduct().getName());
            m.put("quantity", it.getQuantity());
            m.put("unitPrice", it.getUnitPrice());
            m.put("lineTotal", it.getUnitPrice() * it.getQuantity());
            items.add(m);
        }
        receipt.put("items", items);
        receipt.put("taxes", 0.0); // placeholder
        receipt.put("notes", "Comprobante generado automáticamente (simulación)");
        return receipt;
    }
}
