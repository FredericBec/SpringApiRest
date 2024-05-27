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
}
