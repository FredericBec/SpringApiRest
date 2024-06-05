package fr.fms.SpringApiRest.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CategoryDto {
    private String name;

    public CategoryDto(String name){
        this.name=name;
    }

}
