package fr.fms.SpringApiRest.dao;

import fr.fms.SpringApiRest.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
