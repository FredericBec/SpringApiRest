package fr.fms.SpringApiRest.dao;

import fr.fms.SpringApiRest.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileDataRepository extends JpaRepository<FileData, Long> {
}
