package fr.fms.SpringApiRest.web;

import fr.fms.SpringApiRest.entities.Category;
import fr.fms.SpringApiRest.service.ImplTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    ImplTrainingService implTrainingService;

    @GetMapping("/categories")
    public List<Category> getCategories(){
        return implTrainingService.getCategories();
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> saveCategory(@RequestBody Category c){
        Category category = implTrainingService.saveCategory(c);
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
}