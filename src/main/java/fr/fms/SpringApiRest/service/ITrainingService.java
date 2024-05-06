package fr.fms.SpringApiRest.service;

import fr.fms.SpringApiRest.entities.Training;

import java.util.List;
import java.util.Optional;

public interface ITrainingService {
    List<Training> getTrainings();
    Training saveTraining(Training training);
    void deleteTraining(Long id);
    Optional<Training> readTraining(Long id);
}
