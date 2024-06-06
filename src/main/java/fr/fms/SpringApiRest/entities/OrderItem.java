package fr.fms.SpringApiRest.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Setter
@Getter
@Builder
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double price;

    @ManyToOne
    private Training training;

    @ManyToOne
    private Order order;


}
