package fr.fms.SpringApiRest.dao;

import fr.fms.SpringApiRest.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData, Long> {
    Optional<FileData> findByName (String fileName);
}
