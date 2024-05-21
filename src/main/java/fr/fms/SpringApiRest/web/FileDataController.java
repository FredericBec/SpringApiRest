package fr.fms.SpringApiRest.web;

import fr.fms.SpringApiRest.dao.FileDataRepository;
import fr.fms.SpringApiRest.entities.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class FileDataController
{
    @Autowired
    FileDataRepository fileDataRepository;

    @GetMapping("/getPicture")
    public List<FileData> getData(){
        return fileDataRepository.findAll() ;
    }

}
