package fr.fms.SpringApiRest.web;


import fr.fms.SpringApiRest.entities.Customer;
import fr.fms.SpringApiRest.entities.Order;
import fr.fms.SpringApiRest.entities.OrderItem;
import fr.fms.SpringApiRest.entities.Training;
import fr.fms.SpringApiRest.service.ImplBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Date;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private ImplBusinessService implBusinessService;

    @PostMapping("/customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customerBody){
        Customer customer = implBusinessService.saveCustomer(customerBody);
        if(Objects.isNull(customer)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/order")
    public ResponseEntity<Order> saveOrder(@RequestBody Order orderBody){
        Order order = implBusinessService.saveOrder(new Order(null, new Date(), orderBody.getTotalAmount(), orderBody.getCustomer(), null));
        if(Objects.isNull(order)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/orderItem")
    public ResponseEntity<OrderItem> saveOrderItem(@RequestBody OrderItem orderItemBody){
        OrderItem orderItem = implBusinessService.saveOrderItem(new OrderItem(null, orderItemBody.getQuantity(), orderItemBody.getPrice(), orderItemBody.getTraining(), orderItemBody.getOrder()));
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

    @GetMapping("/order/{customer}")
    public Order getOrderByCustomerId(@PathVariable("customer") Customer customer){
        return implBusinessService.getOrder(customer);
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Long id){
        return implBusinessService.getCustomerById(id);
    }
}
