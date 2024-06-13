package fr.fms.SpringApiRest.web;

import fr.fms.SpringApiRest.entities.Training;
import fr.fms.SpringApiRest.service.ImplTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class PicturesController
{
    private final ImplTrainingService implTrainingService;

    @Autowired
    public PicturesController (ImplTrainingService implTrainingService)
    {
        this.implTrainingService = implTrainingService;
    }

//    @GetMapping("/{filename:.+}")
//    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
//        Resource image = storageService.loadImage(filename);
//        String contentType;
//        try {
//            contentType = Files.probeContentType(image.getFile().toPath());
//        } catch (IOException ex) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFilename() + "\"")
//                .body(image);
//    }
//
//    @PostMapping("/upload/{idTraining}")
//    public ResponseEntity<?> uploadImageToSystem (@PathVariable Long idTraining , @RequestParam("file") MultipartFile file) throws IOException
//    {
//        Optional<Training> training = implTrainingService.readTraining(idTraining);
//        if(training.isPresent())
//        {
//            training.get().setImageName(file.isEmpty() ? "default.png" : file.getOriginalFilename());
//        }
//        String uploadImage = storageService.uploadImage(file);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(uploadImage);
//    }
    @GetMapping(path = "photo/{id}")
    public ResponseEntity<?> getphoto ( @PathVariable("id") Long id) throws  IOException
    {
        byte[] file = null;
        try
        {
            Training training = implTrainingService.readTraining(id).get();
            if(training.getImageName() == null)
            {
                training.setImageName("default.png");
            }

            File imgFile = new File(System.getProperty("user.home") + "/Pictures/trainings/" + training.getImageName());
            BufferedImage image = ImageIO.read(imgFile);
            int minWidth = 400;
            int minHeight = 400;
            int maxWidth = 400;
            int maxHeight = 400;
            int width = image.getWidth();
            int height = image.getHeight();

            if (width > maxWidth || height > maxHeight || width < minWidth || height < minHeight) {
                float aspectRatio = (float) width / height;
                if (aspectRatio > 1) {
                    width = maxWidth;
                    height = (int) (maxWidth / aspectRatio);
                } else {
                    height = maxHeight;
                    width = (int) (maxHeight * aspectRatio);
                }

                BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(image, 0, 0, width, height, null);
                g.dispose();

                // Convertir l'image redimensionnée en tableau d'octets
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(resizedImage, "png", baos);
                file = baos.toByteArray();

            } else {
                // Si l'image est déjà dans les dimensions spécifiées, la lire directement en tableau d'octets
                file = Files.readAllBytes(imgFile.toPath());
            }
        }
        catch (Exception e)
        {
            System.out.println("erreur getphoto");
        }
        return ResponseEntity.ok().body(file);
    }

    @PostMapping(path = "/photo/{id}")
    public ResponseEntity<?> uploadPhoto ( MultipartFile file , @PathVariable Long id ) throws Exception
    {
        try
        {
            Training training = implTrainingService.readTraining(id).get();
            training.setImageName(file.getOriginalFilename());
            Files.write(Paths.get(System.getProperty("user.home") + "/Pictures/trainings/" + training.getImageName()), file.getBytes());
            implTrainingService.saveTraining(training);
        }
        catch (Exception e)
        {
            System.out.println("error upload photo");
        }
        return ResponseEntity.ok().build();    }
}


//    File imgFile = new File(System.getProperty("user.home") + "/Pictures/trainings/" + training.getImageName());
//    BufferedImage image = ImageIO.read(imgFile);
//    int maxWidth = 300;
//    int maxHeight = 300;
//    int width = image.getWidth();
//    int height = image.getHeight();
//
//            if (width > maxWidth || height > maxHeight) {
//                    float aspectRatio = (float) width / height;
//                    if (aspectRatio > 1) {
//                    width = maxWidth;
//                    height = (int) (maxWidth / aspectRatio);
//                    } else {
//                    height = maxHeight;
//                    width = (int) (maxHeight * aspectRatio);
//                    }
//
//                    BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//                    Graphics2D g = resizedImage.createGraphics();
//                    g.drawImage(image, 0, 0, width, height, null);
//                    g.dispose();
//
//                    // Convertir l'image redimensionnée en tableau d'octets
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    ImageIO.write(resizedImage, "png", baos);
//                    file = baos.toByteArray();
//                    } else {
//                    // Si l'image est déjà dans les dimensions spécifiées, la lire directement en tableau d'octets
//                    file = Files.readAllBytes(imgFile.toPath());
//                    }