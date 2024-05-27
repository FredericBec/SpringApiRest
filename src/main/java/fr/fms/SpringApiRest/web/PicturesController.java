package fr.fms.SpringApiRest.web;

import fr.fms.SpringApiRest.entities.Training;
import fr.fms.SpringApiRest.service.ImplTrainingService;
import fr.fms.SpringApiRest.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class PicturesController
{
    private final StorageService storageService;
    private final ImplTrainingService implTrainingService;

    @Autowired
    public PicturesController ( StorageService storageService , ImplTrainingService implTrainingService)
    {
        this.storageService = storageService;
        this.implTrainingService = implTrainingService;
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        Resource image = storageService.loadImage(filename);
        String contentType;
        try {
            contentType = Files.probeContentType(image.getFile().toPath());
        } catch (IOException ex) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFilename() + "\"")
                .body(image);
    }

    @PostMapping("/upload/{idTraining}")
    public ResponseEntity<?> uploadImageToSystem (@PathVariable Long idTraining , @RequestParam("file") MultipartFile file) throws IOException
    {
        Optional<Training> training = implTrainingService.readTraining(idTraining);
        if(training.isPresent())
        {
            training.get().setImageName(file.getOriginalFilename());
        }
        String uploadImage = storageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }
}