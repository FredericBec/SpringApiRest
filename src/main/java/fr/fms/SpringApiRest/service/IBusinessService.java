package fr.fms.SpringApiRest.service;

import fr.fms.SpringApiRest.entities.Customer;
import fr.fms.SpringApiRest.entities.Order;
import fr.fms.SpringApiRest.entities.OrderItem;

import java.util.List;

public interface IBusinessService {

    Customer saveCustomer(Customer customer);
    Customer getCustomerById(Long customerId);
    Order saveOrder(Order order);
    Order getOrder(Long id);
    OrderItem saveOrderItem(OrderItem orderItem);
}
