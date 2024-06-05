package fr.fms.SpringApiRest.dto;

import fr.fms.SpringApiRest.entities.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TrainingDto {

    private  String name;
    private String description;
    private double price;
    private int quantity;
    private String imageName;
    private Category category;


    public TrainingDto(String name, String description, double price, int quantity, String imageName, Category category){
        this.name=name;
        this.description=description;
        this.price=price;
        this.quantity=quantity;
        this.imageName=imageName;
        this.category=category;
    }
}
