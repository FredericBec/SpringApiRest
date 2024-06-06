package fr.fms.SpringApiRest.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class OrderDto {

    private Date date;
    private double totalAmount;

    public OrderDto (Date date, double totalAmount){
        this.date=date;
        this.totalAmount=totalAmount;
    }
}
