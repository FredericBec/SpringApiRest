package fr.fms.SpringApiRest.Mapper;

import fr.fms.SpringApiRest.dto.TrainingDto;
import fr.fms.SpringApiRest.entities.Training;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    public TrainingDto mapToDTO (Training training){

        TrainingDto trainingDto = TrainingDto.builder()
                .name(training.getName())
                .description(training.getDescription())
                .price(training.getPrice())
                .quantity(training.getQuantity())
                .imageName(training.getImageName())
                .active(training.isActive())
                .onOrder(training.isOnOrder())
                .place(training.getPlace())
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
                .active(trainingDto.isActive())
                .onOrder(trainingDto.isOnOrder())
                .place(trainingDto.getPlace())
                .build();

        return training;
    }

}
