package fr.fms.SpringApiRest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString @Setter
@Getter
@Builder
@Table(name = "t_order")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties(value = "orders" )
    private Customer customer;


    @OneToMany(mappedBy = "order")
    private Collection<OrderItem> orderItems;


    public Collection<OrderItem> getItems() {
        return orderItems;
    }
}
