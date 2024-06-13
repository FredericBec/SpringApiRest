package fr.fms.SpringApiRest.dao;

import fr.fms.SpringApiRest.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TrainingRepository extends JpaRepository<Training, Long> {
    //List<Training> findAll();
    List<Training> findByCategoryId(Long categoryId);
    List<Training> findByActiveTrue();
    List<Training> findByCategoryIdAndActiveTrue(Long categoryId);
}
