package fr.fms.SpringApiRest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.fms.SpringApiRest.Mapper.TrainingMapper;
import fr.fms.SpringApiRest.dao.CategoryRepository;
import fr.fms.SpringApiRest.dao.RoleRepository;
import fr.fms.SpringApiRest.dao.TrainingRepository;
import fr.fms.SpringApiRest.dao.UserRepository;
import fr.fms.SpringApiRest.dto.TrainingDto;
import fr.fms.SpringApiRest.entities.Category;
import fr.fms.SpringApiRest.entities.Training;
import fr.fms.SpringApiRest.service.AccountServiceImpl;
import fr.fms.SpringApiRest.service.ImplTrainingService;
import fr.fms.SpringApiRest.web.TrainingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;



@WebMvcTest(controllers = TrainingController.class)
class TrainingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImplTrainingService implTrainingService;

    @MockBean
    private AccountServiceImpl accountService;

    @MockBean
    private TrainingMapper trainingMapper;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private TrainingRepository trainingRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
     void testGetTrainings() throws Exception {
        mockMvc.perform(get("/api/trainings"))
                .andExpect(status().isOk());
    }

    @Test
     void testSaveTraining() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // DTO utilisé dans la requête (si applicable)
        TrainingDto trainingDto = new TrainingDto("Java", "Java Standard Edition 8 sur 5 jours", 3500.0, 1, null, null, true, false, 10);

        // Entité attendue après mappage
        Training testTraining = new Training(null, "Java", "Java Standard Edition 8 sur 5 jours", 3500.0, 1, false, true, 10, null, null);

        String requestContent = objectMapper.writeValueAsString(trainingDto);

        // Configuration des mocks
        when(trainingMapper.mapToEntity(any(TrainingDto.class))).thenReturn(testTraining);
        when(implTrainingService.saveTraining(any(Training.class))).thenReturn(testTraining);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/trainings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isCreated())
                .andReturn();

        // Vérifiez la réponse si nécessaire
        String jsonResponse = result.getResponse().getContentAsString();
        Training responseTraining = objectMapper.readValue(jsonResponse, Training.class);
        assertEquals("Java", responseTraining.getName());
        assertEquals("Java Standard Edition 8 sur 5 jours", responseTraining.getDescription());
    }

    @Test
     void testTrainingsByCategory() throws Exception {
        Long categoryId = 1L;
        List<Training> trainings = Arrays.asList(
                new Training(1L, "Java", "Java Standard Edition 8 sur 5 jours", 3500.0, 1, false,true , 10 ,"java.png", null),
                new Training(2L, "Spring", "Spring Framework sur 5 jours", 4000.0, 1, false,true , 10 ,"spring.png", null)
        );

        when(implTrainingService.getTrainingsByCategory(categoryId)).thenReturn(trainings);

        MvcResult result = mockMvc.perform(get("/api/trainings/category/{id}", categoryId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(trainings);
        String actualJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, actualJson);
    }

    @Test
     void testDeleteTraining() throws Exception {
        Long trainingId = 1L;

        doNothing().when(implTrainingService).deleteTraining(trainingId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/trainings/{id}", trainingId))
                .andExpect(status().isNoContent());
    }

    @Test
     void testGetTrainingByIdNotFound() throws Exception {
        Long trainingId = 1L;

        when(implTrainingService.readTraining(trainingId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/trainings/{id}", trainingId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateTraining() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        Training existingTraining = new Training(1L, "formation", "description", 1000, 1, false,true , 10 ,"formation.png", null);
        Training updateTraining = new Training(1L, "updated formation", "updated description", 2000, 1, false,true , 10 ,"formation.png", null);

        when(implTrainingService.readTraining(existingTraining.getId())).thenReturn(Optional.of(existingTraining));
        when(implTrainingService.saveTraining(updateTraining)).thenReturn(updateTraining);


        String requestContent = objectMapper.writeValueAsString(updateTraining);

        MvcResult result = (MvcResult) mockMvc.perform(MockMvcRequestBuilders.post("/api/update/{id}",existingTraining.getId())
                .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("updated formation")))
                .andExpect(jsonPath("$.description", is("updated description")))
                .andExpect(jsonPath("$.price", is(2000.0)))
                .andExpect(jsonPath("$.quantity", is(1)))
                .andExpect(jsonPath("$.imageName",is("formation.png")))
                .andReturn();





    }

}





