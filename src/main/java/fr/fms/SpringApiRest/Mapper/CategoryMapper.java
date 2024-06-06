package fr.fms.SpringApiRest.Mapper;

import fr.fms.SpringApiRest.dto.CategoryDto;
import fr.fms.SpringApiRest.entities.Category;
import org.springframework.stereotype.Component;


@Component
public class CategoryMapper {

    public CategoryDto mapToDto (Category category){
        CategoryDto categoryDto = CategoryDto.builder()
                .name(category.getName())
                .build();

        return categoryDto;
    }

    public Category mapToEntity (CategoryDto categoryDto){
        Category category = Category.builder()
                .name(categoryDto.getName())
                .build();

        return category;
    }
}
