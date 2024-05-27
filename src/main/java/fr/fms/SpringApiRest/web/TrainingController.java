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
        return ResponseEntity.created(location).build();
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
