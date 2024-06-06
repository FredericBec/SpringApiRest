package fr.fms.SpringApiRest.Mapper;

import fr.fms.SpringApiRest.dto.CategoryDto;
import fr.fms.SpringApiRest.dto.OrderDto;
import fr.fms.SpringApiRest.entities.Order;
import org.springframework.stereotype.Component;


@Component
public class OrderMapper {

    public OrderDto mapToDto (Order order){
        OrderDto orderDto = OrderDto.builder()
                .date(order.getDate())
                .totalAmount(order.getTotalAmount())
                .build();

        return orderDto;
    }

    public Order mapToEntity (OrderDto orderDto){
        Order order = Order.builder()
                .date(orderDto.getDate())
                .totalAmount(orderDto.getTotalAmount())
                .build();

           return order;
    }
}
