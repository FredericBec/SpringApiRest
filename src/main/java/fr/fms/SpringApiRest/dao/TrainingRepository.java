package fr.fms.SpringApiRest.dao;

import fr.fms.SpringApiRest.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

//@CrossOrigin("*")
//@RepositoryRestResource
public interface TrainingRepository extends JpaRepository<Training, Long> {
    //List<Training> findAll();
}
