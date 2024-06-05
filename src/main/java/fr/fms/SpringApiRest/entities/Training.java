package fr.fms.SpringApiRest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Training implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private boolean onOrder;
    private boolean active;
    private String imageName;

//    @ManyToOne
//    @JsonIgnoreProperties(value = "trainings")
//    private FileData fileData;

    @ManyToOne
    @JsonIgnoreProperties(value = "trainings")
    private Category category;

//    public Training ( Long id , String name , String description , double price , int quantity , String imageName , Category category)
//    {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.quantity = quantity;
//        this.imageName = imageName;
//        this.category = category;
//    }
}
