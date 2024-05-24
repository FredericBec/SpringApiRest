package fr.fms.SpringApiRest.dao;

import fr.fms.SpringApiRest.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByCustomerIdOrderByIdDesc(Long customerId);

    List<Order> findByCustomerId(Long customerId);
}
