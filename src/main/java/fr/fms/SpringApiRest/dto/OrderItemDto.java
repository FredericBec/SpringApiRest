package fr.fms.SpringApiRest.dto;


import fr.fms.SpringApiRest.entities.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OrderItemDto {

    private int quantity;
    private double price;

    public OrderItemDto (int quantity, double price){
        this.quantity=quantity;
        this.price=price;
    }
}
