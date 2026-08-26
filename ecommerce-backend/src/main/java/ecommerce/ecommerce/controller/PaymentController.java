package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.dto.PaymentRequest;
import ecommerce.ecommerce.entity.Payment;
import ecommerce.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payments")
    @PreAuthorize("hasAnyRole('ADMIN','WORKER','CLIENT')")
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }
}
