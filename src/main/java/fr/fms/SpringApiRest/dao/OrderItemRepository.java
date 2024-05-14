package fr.fms.SpringApiRest.dao;

import fr.fms.SpringApiRest.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
