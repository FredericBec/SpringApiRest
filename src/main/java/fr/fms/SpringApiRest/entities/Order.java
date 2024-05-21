package fr.fms.SpringApiRest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Table(name = "t_order")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double totalAmount;

    @ManyToOne
    private Customer customer;


    @OneToMany(mappedBy = "order")
    private Collection<OrderItem> orderItems;


    public Collection<OrderItem> getItems() {
        return orderItems;
    }
}
