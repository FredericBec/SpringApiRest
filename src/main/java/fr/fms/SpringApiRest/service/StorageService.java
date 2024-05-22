package fr.fms.SpringApiRest.service;

import fr.fms.SpringApiRest.dao.FileDataRepository;
import fr.fms.SpringApiRest.entities.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class StorageService
{
    @Value("${external.images.path}")
    private String imagesPath;

    @Autowired
    private FileDataRepository fileDataRepository;

    private final Path FOLDER_PATH = Paths.get("imagesApi/").toAbsolutePath().normalize();

    public String uploadImage (MultipartFile file) throws IOException
    {
        String filePath = FOLDER_PATH.resolve(file.getOriginalFilename()).toString() ;
        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null)
        {
            return "file _ upload : " + filePath;
        }
        return null;
    }

    public byte[] downloadImage(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public byte[] downloadImageId(Long id) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findById(id);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

//    public byte[] downloadAllImage() throws IOException {
//        List<FileData> fileData = fileDataRepository.findAll();
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//        for(FileData data : fileData)
//        {
//            String filePath = fileData.get(data.getId()).getFilePath();
//            byte[] images = Files.readAllBytes(new File(filePath).toPath());
//            outputStream.write(images);
//        }
//        return outputStream.toByteArray();
//    }

    public Resource loadImage(String filename) {
        try {
            Path filePath = Paths.get(imagesPath).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Image not found or not readable: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error loading image: " + filename, e);
        }
    }
}
