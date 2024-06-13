package fr.fms.SpringApiRest.web;

import fr.fms.SpringApiRest.Mapper.TrainingMapper;
import fr.fms.SpringApiRest.dto.TrainingDto;
import fr.fms.SpringApiRest.entities.Training;
import fr.fms.SpringApiRest.exception.RecordNotFoundException;
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
public class TrainingController {
    @Autowired
    private ImplTrainingService implTrainingService;

    @Autowired
    private TrainingMapper trainingMapper;

    @GetMapping("/trainings")
    public List<Training> allTrainings(){
        List <Training> lT = implTrainingService.getTrainings();
        return implTrainingService.getTrainings();
    }

    @GetMapping("/trainings/category/{id}")
    public List<Training> trainingsByCategory(@PathVariable("id") Long id){
        return implTrainingService.getTrainingsByCategory(id);
    }

    @PostMapping("/trainings")
    public ResponseEntity<Training> saveTraining(@RequestBody TrainingDto trainingDto){
        Training training = implTrainingService.saveTraining(trainingMapper.mapToEntity(trainingDto));
        if(Objects.isNull(training)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(training.getId())
                .toUri();
        return ResponseEntity.created(location).body(training);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Training> updateTraining(@PathVariable("id") Long id , @RequestBody TrainingDto trainingDto){
        Optional<Training> training = implTrainingService.readTraining(id);
        System.out.println(training);
        if(training.isPresent())
        {
            Training train = training.get();
            train.setDescription(trainingDto.getDescription());
            train.setName(trainingDto.getName());
            train.setPrice(trainingDto.getPrice());
            train.setCategory(trainingDto.getCategory());
            train.setActive(trainingDto.isActive());
            train.setPlace(trainingDto.getPlace());
            if(trainingDto.getPlace() > 0)
            {
                train.setOnOrder(false);
            }
            else
            {
                train.setOnOrder(true);
            }
            implTrainingService.saveTraining(train);
            return ResponseEntity.ok(train); // Retourne le Training mis à jour avec un statut 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Retourne un statut 404 Not Found si l'entité n'est pas trouvée
        }
    }

    @DeleteMapping("/trainings/{id}")
    public ResponseEntity<Void>deleteTraining(@PathVariable("id") Long id){
        implTrainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/trainings/{id}")
    public Training getTrainingById(@PathVariable("id") Long id){

        return implTrainingService.readTraining(id)
                .orElseThrow(() -> new RecordNotFoundException("Id de formation " + id + " n'existe pas"));
    }

    @GetMapping("/trainingsActive")
    public List<Training> allTrainingsActive()
    {
        List <Training> lT = implTrainingService.getByActive();
        return implTrainingService.getByActive();
    }

    @GetMapping("/trainings/categoryActive/{id}")
    public List<Training> trainingsByCategoryActive(@PathVariable("id") Long id)
    {
        return implTrainingService.getTrainingsByCategoryAndActive(id);
    }

    @PostMapping("/desactivatedTraining")
    public ResponseEntity<Training> desactivatedTraining(@RequestBody Training t)
    {
        Optional<Training> training = implTrainingService.readTraining(t.getId());
        if(training.isPresent())
        {
            Training train = training.get();
            train.setActive(false);
            implTrainingService.saveTraining(train);
            return ResponseEntity.ok(train); // Retourne le Training mis à jour avec un statut 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Retourne un statut 404 Not Found si l'entité n'est pas trouvée
        }
    }

    @PostMapping("/updateOnOrder/{id}")
    public ResponseEntity<Training> updateOnOrder(@PathVariable("id") Long id ,@RequestBody int place)
    {
        Optional<Training> training = implTrainingService.readTraining(id);
        if(training.isPresent())
        {
            Training train = training.get();
            train.setPlace(train.getPlace() - place);
            if(train.getPlace() < 1)
            {
                train.setOnOrder(true);
            }
            implTrainingService.saveTraining(train);
            return ResponseEntity.ok(train); // Retourne le Training mis à jour avec un statut 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Retourne un statut 404 Not Found si l'entité n'est pas trouvée
        }
    }

    @PostMapping("/activatedTraining")
    public ResponseEntity<Training> activatedTraining(@RequestBody Training t)
    {
        Optional<Training> training = implTrainingService.readTraining(t.getId());
        if(training.isPresent())
        {
            Training train = training.get();
            train.setActive(true);
            implTrainingService.saveTraining(train);
            return ResponseEntity.ok(train); // Retourne le Training mis à jour avec un statut 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Retourne un statut 404 Not Found si l'entité n'est pas trouvée
        }
    }
}
