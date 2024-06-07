package fr.fms.SpringApiRest.Mapper;

import fr.fms.SpringApiRest.dto.OrderItemDto;
import fr.fms.SpringApiRest.entities.OrderItem;
import org.springframework.stereotype.Component;


@Component
public class OrderItemMapper {
    public OrderItemDto mapToDto (OrderItem orderItem){
        OrderItemDto orderItemDto = OrderItemDto.builder()
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .order(orderItem.getOrder())
                .build();

        return orderItemDto;
    }

    public OrderItem mapToEntity (OrderItemDto orderItemDto){
        OrderItem orderItem = OrderItem.builder()
                .quantity(orderItemDto.getQuantity())
                .price(orderItemDto.getPrice())
                .order(orderItemDto.getOrder())
                .build();

        return orderItem;
    }
}
