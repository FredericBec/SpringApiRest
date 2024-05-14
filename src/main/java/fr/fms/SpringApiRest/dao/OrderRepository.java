package fr.fms.SpringApiRest.dao;

import fr.fms.SpringApiRest.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByCustomerIdOrderByIdDesc(Long customerId);
}
