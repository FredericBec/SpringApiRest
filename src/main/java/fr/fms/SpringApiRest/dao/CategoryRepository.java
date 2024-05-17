package fr.fms.SpringApiRest.dao;

import fr.fms.SpringApiRest.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
