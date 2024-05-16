package fr.fms.SpringApiRest;

import fr.fms.SpringApiRest.dao.CategoryRepository;
import fr.fms.SpringApiRest.dao.TrainingRepository;
import fr.fms.SpringApiRest.entities.Category;
import fr.fms.SpringApiRest.entities.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestTrainings
{

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void dbbAdd()
    {
        Category cat1 = categoryRepository.save(new Category(null , "front" , null));
        Category cat2 = categoryRepository.save(new Category(null , "back" , null));
        trainingRepository.save(new Training(null , "Java" , "back" , 10 , 1 , null , cat2));
        trainingRepository.save(new Training(null , "javascript" , "front" , 20 , 1 , null , cat1));
        trainingRepository.save(new Training(null , "php" , "back" , 30 , 1 , null , cat2));
    }

    @Test
    void TestGetAllTrainings()
    {
        List<Training> trainigL = trainingRepository.findAll();
        assertEquals("Java" , trainigL.get(0).getName());
    }
}
