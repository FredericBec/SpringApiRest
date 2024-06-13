package fr.fms.SpringApiRest.dto;


import fr.fms.SpringApiRest.entities.Order;
import fr.fms.SpringApiRest.entities.OrderItem;
import fr.fms.SpringApiRest.entities.Training;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OrderItemDto {

    private int quantity;
    private double price;
    private Training training;
    private Order order;

    public OrderItemDto (int quantity, double price, Training training, Order order){
        this.quantity=quantity;
        this.price=price;
        this.training = training;
        this.order = order;
    }
}
