package ecommerce.ecommerce.repository;

import ecommerce.ecommerce.entity.Order;
import ecommerce.ecommerce.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
