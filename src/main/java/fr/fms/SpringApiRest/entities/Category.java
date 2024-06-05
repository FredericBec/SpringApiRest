package fr.fms.SpringApiRest.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString @Setter @Getter @Builder
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private Collection<Training> trainings;
}
