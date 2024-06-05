package fr.fms.SpringApiRest.Mapper;

import fr.fms.SpringApiRest.dto.TrainingDto;
import fr.fms.SpringApiRest.entities.Training;

public class TrainingMapper {

    public TrainingDto mapToDTO (Training training){

        TrainingDto trainingDto = TrainingDto.builder()
                .name(training.getName())
                .description(training.getDescription())
                .price(training.getPrice())
                .quantity(training.getQuantity())
                .imageName(training.getImageName())
                .build();

        return  trainingDto;
    }

    public  Training mapToEntity (TrainingDto trainingDto){

        Training training = Training.builder()
        .name(trainingDto.getName())
                .description(trainingDto.getDescription())
                .price(trainingDto.getPrice())
                .quantity(trainingDto.getQuantity())
                .imageName(trainingDto.getImageName())
                .build();

        return training;
    }

}
