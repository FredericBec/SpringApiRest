package fr.fms.SpringApiRest.web;


import fr.fms.SpringApiRest.dao.CustomerRepository;
import fr.fms.SpringApiRest.dao.OrderItemRepository;
import fr.fms.SpringApiRest.dao.OrderRepository;
import fr.fms.SpringApiRest.entities.Customer;
import fr.fms.SpringApiRest.entities.Order;
import fr.fms.SpringApiRest.entities.OrderItem;
import fr.fms.SpringApiRest.service.ImplBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private ImplBusinessService implBusinessService;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @PostMapping("/customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customerBody) {
        Customer customer = implBusinessService.saveCustomer(customerBody);
        if (Objects.isNull(customer)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();
        return ResponseEntity.created(location).body(customer);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> saveOrder(@RequestBody Order orderBody) {
            Order order = implBusinessService.saveOrder(new Order(null,new Date(),orderBody.getTotalAmount(), orderBody.getCustomer(),null));
            if (Objects.isNull(order)) {
                return ResponseEntity.noContent().build();
            }
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(order.getId())
                    .toUri();
            return ResponseEntity.created(location).body(order);
        }




    @PostMapping("/orderItem")
    public ResponseEntity<OrderItem> saveOrderItem(@RequestBody OrderItem orderItemBody){
        OrderItem orderItem = implBusinessService.saveOrderItem(orderItemBody);
        if(Objects.isNull(orderItem)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderItem.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/order/{id}")
    public Order getOrderByCustomerId(@PathVariable("id")Long id){
        return implBusinessService.getOrder(id);
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Long id){
        return implBusinessService.getCustomerById(id);
    }
}
