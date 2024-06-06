package fr.fms.SpringApiRest.web;


import fr.fms.SpringApiRest.Mapper.CustomerMapper;
import fr.fms.SpringApiRest.Mapper.OrderMapper;
import fr.fms.SpringApiRest.dao.CustomerRepository;
import fr.fms.SpringApiRest.dao.OrderItemRepository;
import fr.fms.SpringApiRest.dao.OrderRepository;
import fr.fms.SpringApiRest.dto.CustomerDto;
import fr.fms.SpringApiRest.dto.OrderDto;
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
import java.util.List;
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

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private OrderMapper orderMapper;


    @PostMapping("/customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = implBusinessService.saveCustomer(customerMapper.mapToEntity(customerDto));
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
    public ResponseEntity<Order> saveOrder(@RequestBody OrderDto orderDto) {
            Order order = implBusinessService.saveOrder(orderMapper.mapToEntity(orderDto));
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
