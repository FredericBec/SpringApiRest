package fr.fms.SpringApiRest.service;

import fr.fms.SpringApiRest.dao.FileDataRepository;
import fr.fms.SpringApiRest.entities.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class StorageService
{
    @Autowired
    private FileDataRepository fileDataRepository;

    private final Path FOLDER_PATH = Paths.get("images_Trainings/").toAbsolutePath().normalize();

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
}
