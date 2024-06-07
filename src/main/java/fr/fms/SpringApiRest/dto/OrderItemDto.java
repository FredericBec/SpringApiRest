package fr.fms.SpringApiRest.dto;


import fr.fms.SpringApiRest.entities.Order;
import fr.fms.SpringApiRest.entities.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OrderItemDto {
    private  Long id;
    private int quantity;
    private double price;
    private Order order;

    public OrderItemDto (Long id, int quantity, double price, Order order){
        this.id=id;
        this.quantity=quantity;
        this.price=price;
        this.order=order;
    }
}
