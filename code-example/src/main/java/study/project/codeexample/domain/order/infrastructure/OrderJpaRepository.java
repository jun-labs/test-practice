package study.project.codeexample.domain.order.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import study.project.codeexample.domain.order.entity.Order;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
