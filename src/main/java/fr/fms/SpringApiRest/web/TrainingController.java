package fr.fms.SpringApiRest.web;

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
    public ResponseEntity<Training> saveTraining(@RequestBody Training t){
        Training training = implTrainingService.saveTraining(t);
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
    public ResponseEntity<Training> updateTraining(@PathVariable("id") Long id , @RequestBody Training t){
        Optional<Training> training = implTrainingService.readTraining(id);
        System.out.println(training);
        if(training.isPresent())
        {
            Training train = training.get();
            train.setDescription(t.getDescription());
            train.setName(t.getName());
            train.setPrice(t.getPrice());
            train.setCategory(t.getCategory());
            implTrainingService.saveTraining(train);
            return ResponseEntity.ok(train); // Retourne le Training mis à jour avec un statut 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Retourne un statut 404 Not Found si l'entité n'est pas trouvée
        }
    }

    @DeleteMapping("/trainings/{id}")
    public void deleteTraining(@PathVariable("id") Long id){
        implTrainingService.deleteTraining(id);
    }

    @GetMapping("/trainings/{id}")
    public Training getTrainingById(@PathVariable("id") Long id){

        return implTrainingService.readTraining(id)
                .orElseThrow(() -> new RecordNotFoundException("Id de formation " + id + " n'existe pas"));
    }
}
