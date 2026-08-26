package ecommerce.ecommerce.service;

import ecommerce.ecommerce.dto.PaymentRequest;
import ecommerce.ecommerce.entity.Order;
import ecommerce.ecommerce.entity.Payment;
import ecommerce.ecommerce.enums.PaymentStatus;
import ecommerce.ecommerce.repository.OrderRepository;
import ecommerce.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public Payment createPayment(PaymentRequest request) {
        Order order = orderRepository.findById(request.orderId())
            .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setProvider(request.provider());
        payment.setAmount(request.amount());
        payment.setExternalReference(request.externalReference());
        payment.setStatus(PaymentStatus.PAID);
        return paymentRepository.save(payment);
    }
}
