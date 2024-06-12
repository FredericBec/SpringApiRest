package fr.fms.SpringApiRest;

import fr.fms.SpringApiRest.dao.CategoryRepository;
import fr.fms.SpringApiRest.dao.RoleRepository;
import fr.fms.SpringApiRest.dao.TrainingRepository;
import fr.fms.SpringApiRest.dao.UserRepository;
import fr.fms.SpringApiRest.entities.Category;
import fr.fms.SpringApiRest.entities.Training;
import fr.fms.SpringApiRest.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestRepository
{

    @MockBean
    private AccountServiceImpl accountService;

    @MockBean
    private UserDetailsService userDetailsService;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    Category cat1;

    @BeforeEach
    void dbbAdd()
    {
        cat1 = categoryRepository.save(new Category(null , "front" , null));
        Category cat2 = categoryRepository.save(new Category(null , "back" , null));
        trainingRepository.save(new Training(null , "Java" , "back" , 10 , 1 , null , cat2));
        trainingRepository.save(new Training(null , "javascript" , "front" , 20 , 1 , null , cat1));
        trainingRepository.save(new Training(null , "php" , "back" , 30 , 1 , null , cat2));
    }

    @Test
    void TestGetAllTrainings()
    {
        List<Training> trainingL = trainingRepository.findAll();
        assertEquals("Java" , trainingL.get(0).getName());
    }

    @Test
    void TestGetTrainingByCategory()
    {
        List<Training> trainingCat = trainingRepository.findByCategoryId(cat1.getId());
        assertNotNull(trainingCat.size());
    }

    @Test
    void TestGetCategorie()
    {
        List<Category> categories = categoryRepository.findAll();
        assertNotNull(categories.size());
    }
}
