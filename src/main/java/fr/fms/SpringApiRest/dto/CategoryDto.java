package fr.fms.SpringApiRest.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CategoryDto {
    private  Long id;
    private String name;

    public CategoryDto(Long id,String name){
        this.id=id;
        this.name=name;
    }

}
