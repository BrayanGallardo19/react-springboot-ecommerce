package ecommerce.ecommerce.repository;

import ecommerce.ecommerce.entity.Order;
import ecommerce.ecommerce.entity.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrder(Order order);
}
