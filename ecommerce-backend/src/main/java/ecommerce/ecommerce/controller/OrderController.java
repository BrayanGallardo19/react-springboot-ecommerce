package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.dto.OrderRequest;
import ecommerce.ecommerce.entity.Order;
import ecommerce.ecommerce.service.OrderService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/client/orders")
    @PreAuthorize("hasAnyRole('ADMIN','WORKER','CLIENT')")
    public ResponseEntity<Order> createOrder(Authentication authentication, @RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(authentication, request));
    }

    @GetMapping("/client/orders")
    @PreAuthorize("hasAnyRole('ADMIN','WORKER','CLIENT')")
    public ResponseEntity<List<Order>> getOrders(Authentication authentication) {
        return ResponseEntity.ok(orderService.findByCurrentUser(authentication));
    }

    // Simple receipt/boleta generator (JSON placeholder). In production replace with proper SII integration and PDF.
    @GetMapping("/client/orders/{orderId}/receipt")
    @PreAuthorize("hasAnyRole('ADMIN','WORKER','CLIENT')")
    public ResponseEntity<Map<String, Object>> getOrderReceipt(Authentication authentication, @PathVariable Long orderId) {
        Map<String, Object> receipt = orderService.generateReceipt(authentication, orderId);
        return ResponseEntity.ok(receipt);
    }
}
