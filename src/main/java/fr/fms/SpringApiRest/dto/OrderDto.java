package fr.fms.SpringApiRest.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.fms.SpringApiRest.entities.Customer;
import fr.fms.SpringApiRest.entities.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class OrderDto {

    private  Long id;
    private Date date;
    private double totalAmount;
    private Customer customer;


    public OrderDto (Long id,Date date, double totalAmount, Customer customer){
        this.id=id;
        this.date=date;
        this.totalAmount=totalAmount;
        this.customer=customer;
    }
}
