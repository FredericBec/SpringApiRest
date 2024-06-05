package fr.fms.SpringApiRest.service;

import fr.fms.SpringApiRest.entities.*;

import java.util.List;
import java.util.Optional;

public interface ITrainingService {
    List<Training> getTrainings();
    List<Training> getTrainingsByCategory(Long categoryId);
    Training saveTraining(Training training);
    void deleteTraining(Long id);
    Optional<Training> readTraining(Long id);
    List<Category> getCategories();
    Category saveCategory(Category category);
    void deleteCategory(Long id);
    Optional<Category> findCatById(Long id);
    List<Training> getByActive();
    List<Training> getTrainingsByCategoryAndActive(Long categoryId);
}
