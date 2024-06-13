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
    private boolean active;
    private boolean onOrder;
    private int place;


    public TrainingDto(String name, String description, double price, int quantity, String imageName, Category category, boolean active, boolean onOrder, int place){
        this.name=name;
        this.description=description;
        this.price=price;
        this.quantity=quantity;
        this.imageName=imageName;
        this.category=category;
        this.active = active;
        this.onOrder = onOrder;
        this.place = place;
    }
}
