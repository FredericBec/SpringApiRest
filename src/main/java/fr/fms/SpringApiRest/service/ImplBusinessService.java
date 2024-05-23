package fr.fms.SpringApiRest.service;

import fr.fms.SpringApiRest.dao.CustomerRepository;
import fr.fms.SpringApiRest.dao.OrderItemRepository;
import fr.fms.SpringApiRest.dao.OrderRepository;
import fr.fms.SpringApiRest.entities.Customer;
import fr.fms.SpringApiRest.entities.Order;
import fr.fms.SpringApiRest.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImplBusinessService implements IBusinessService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> optional = customerRepository.findById(customerId);
        return (optional.isPresent()) ? optional.get() : null;
    }

    @Override
    public Order saveOrder(Order order) {
        if (order.getCustomer() != null && order.getCustomer().getId() == null) {
        Customer savedCustomer = customerRepository.save(order.getCustomer());
        order.setCustomer(savedCustomer);
    }
        return orderRepository.save(order);
}

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findByCustomerIdOrderByIdDesc(id);
    }

    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
