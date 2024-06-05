package fr.fms.SpringApiRest.web;

import fr.fms.SpringApiRest.dao.TrainingRepository;
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
    private TrainingRepository trainingRepository;

    @GetMapping("/trainings")
    public List<Training> allTrainings()
    {
        List <Training> lT = implTrainingService.getTrainings();
        return implTrainingService.getTrainings();
    }

    @GetMapping("/trainings/category/{id}")
    public List<Training> trainingsByCategory(@PathVariable("id") Long id)
    {
        return implTrainingService.getTrainingsByCategory(id);
    }

    @PostMapping("/trainings")
    public ResponseEntity<Training> saveTraining(@RequestBody Training t)
    {
        Training training = implTrainingService.saveTraining(t);
        if(Objects.isNull(training))
        {
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
    public ResponseEntity<Training> updateTraining(@PathVariable("id") Long id , @RequestBody Training t)
    {
        Optional<Training> training = implTrainingService.readTraining(id);
        System.out.println("training " + training);
        if(training.isPresent())
        {
            Training train = training.get();
            train.setDescription(t.getDescription());
            train.setName(t.getName());
            train.setPrice(t.getPrice());
            train.setCategory(t.getCategory());
            implTrainingService.saveTraining(train);
            System.out.println("train " + train);
            return ResponseEntity.ok(train); // Retourne le Training mis à jour avec un statut 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Retourne un statut 404 Not Found si l'entité n'est pas trouvée
        }
    }

    @DeleteMapping("/trainings/{id}")
    public ResponseEntity<Void>deleteTraining(@PathVariable("id") Long id)
    {
        Optional<Training> getTrain = implTrainingService.readTraining(id);
        if(getTrain.isPresent())
        {
            if(getTrain.get().isOnOrder()) {
                Training train = getTrain.get();
                train.setActive(false);
                implTrainingService.saveTraining(train);
            }
            else
            {
                implTrainingService.deleteTraining(id);
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/trainings/{id}")
    public Training getTrainingById(@PathVariable("id") Long id)
    {
        return implTrainingService.readTraining(id)
                .orElseThrow(() -> new RecordNotFoundException("Id de formation " + id + " n'existe pas"));
    }

    @PostMapping("/updateOnOrder")
    public ResponseEntity<Training> updateOnOrder(@RequestBody Long t)
    {
        Optional<Training> training = implTrainingService.readTraining(t);
        System.out.println("training " + training);
        if(training.isPresent())
        {
            Training train = training.get();
            train.setOnOrder(true);
            implTrainingService.saveTraining(train);
            System.out.println("train " + train);
            return ResponseEntity.ok(train); // Retourne le Training mis à jour avec un statut 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Retourne un statut 404 Not Found si l'entité n'est pas trouvée
        }
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

}
