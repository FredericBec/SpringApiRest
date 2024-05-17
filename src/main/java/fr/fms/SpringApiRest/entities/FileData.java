package fr.fms.SpringApiRest.entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "File_Data")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FileData
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private String filePath;

    @ManyToOne
    private Training training;

    public FileData ( Long id , String name , String type , String filePath)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.filePath = filePath;
    }
}
