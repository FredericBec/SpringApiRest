package fr.fms.SpringApiRest;

import fr.fms.SpringApiRest.dao.*;
import fr.fms.SpringApiRest.dto.TrainingDto;
import fr.fms.SpringApiRest.entities.*;
import fr.fms.SpringApiRest.service.ImplTrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class TestTrainingService
{
    private static Customer custom1;
    private static Customer custom2;
    private static Customer custom3;
    private static Category cat1;
    private static Category cat2;
    private static Category cat3;
    private static Training train1;
    private static Training train2;
    private static Training train3;

    @Mock
    TrainingRepository trainingRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    ImplTrainingService trainingService;

    @BeforeEach
    void dbbAdd()
    {
        custom1 = new Customer(null , "Nel" , "itai" , "12rue tulipes" , "nel@hot.fr" , "0554585652" , null );
        custom2 = new Customer(null , "Angel" , "isha" , "usa" , "ang@hot.com" , "0554585652" , null );
        custom3 = new Customer(null , "Bubba" , "isha" , "florida" , "bub@hot.com" , "0554585652" , null );

        cat1 = new Category(null , "front" , null );
        cat2 = new Category(2L, "back" , null );
        cat3 = new Category(null , "app" , null );

        train1 = new Training(null , "Java" , "back" , 10 , 1 , null , cat2 );
        train2 = new Training(null , "javascript" , "front" , 20 , 1 , null , cat1 );
        train3 = new Training(null , "php" , "back" , 30 , 1 , null , cat3 );
    }

    @Test
    void getTrainingsByCategoryTEST()
    {
        //given
        List<Training> trainingList = new ArrayList<>();
        trainingList.add(train1);
        when(trainingRepository.findById(train1.getId())).thenReturn(Optional.ofNullable(train1));

        //when
        final Optional<Training> expectedResult = trainingService.readTraining(train1.getId());

        //Then
        verify(trainingRepository).findById(train1.getId());
        assertNotEquals(0 , expectedResult.isPresent());
    }

    @Test
    void getTrainings()
    {
        //given
        List<Training> trainingList = new ArrayList<>();
        trainingList.add(train1);
        trainingList.add(train2);

      when(trainingRepository.findAll()).thenReturn(trainingList);

        //when
        final List<Training> expectedResult = trainingService.getTrainings();

        //Then
        verify(trainingRepository).findAll();
        assertNotEquals(0 , expectedResult.size());
    }

    @Test
    void getTrainingsByIdTEST()
    {
        //given
        List<Training> trainingList = new ArrayList<>();
        trainingList.add(train1);
        when(categoryRepository.findById(cat2.getId())).thenReturn(Optional.ofNullable(cat2));
        when(trainingRepository.findByCategoryId(cat2.getId())).thenReturn(trainingList);

        //when
        final List<Training> expectedResult = trainingService.getTrainingsByCategory(cat2.getId());

        //Then
        verify(trainingRepository).findByCategoryId(cat2.getId());
        assertNotEquals(0 , expectedResult.size());
    }

    @Test
    void getCategoryByIdTEST()
    {
        //given
        when(categoryRepository.findById(cat2.getId())).thenReturn(Optional.ofNullable(cat2));
        //when
        final Optional<Category> expectedResult = trainingService.findCatById(cat2.getId());

        //Then
        verify(categoryRepository).findById(cat2.getId());
        assertNotEquals(0 , expectedResult.get().getId());
    }
}