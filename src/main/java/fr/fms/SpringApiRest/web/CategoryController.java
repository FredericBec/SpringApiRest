package fr.fms.SpringApiRest.web;

import fr.fms.SpringApiRest.Mapper.CategoryMapper;
import fr.fms.SpringApiRest.dto.CategoryDto;
import fr.fms.SpringApiRest.entities.Category;
import fr.fms.SpringApiRest.service.ImplTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    ImplTrainingService implTrainingService;

    @Autowired
    CategoryMapper categoryMapper;

    @GetMapping("/categories")
    public List<Category> getCategories(){
        return implTrainingService.getCategories();
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> saveCategory(@RequestBody CategoryDto categoryDto){
        Category category = implTrainingService.saveCategory(categoryMapper.mapToEntity(categoryDto));
        if(Objects.isNull(category)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        implTrainingService.deleteCategory(id);
    }

    @GetMapping("/categoriesid/{id}")
    public Optional<Category> getCatById(@PathVariable("id") Long id)
    {
        return implTrainingService.findCatById(id);
    }
}
