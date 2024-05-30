package fr.fms.SpringApiRest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.fms.SpringApiRest.dao.CategoryRepository;
import fr.fms.SpringApiRest.dao.TrainingRepository;
import fr.fms.SpringApiRest.entities.Training;
import fr.fms.SpringApiRest.service.ImplTrainingService;
import fr.fms.SpringApiRest.web.TrainingController;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TrainingController.class)
public class TrainingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImplTrainingService implTrainingService;

    @MockBean
    private TrainingRepository trainingRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void testGetTrainings() throws Exception
    {
        mockMvc.perform(get("/api/trainings"))
                .andExpect(status().isOk());
    }
    @Test
    public void testSaveTraining() throws Exception {


        ObjectMapper objectMapper = new ObjectMapper();

        Training testTraining = new Training(null, "Java", "Java Standard Edition 8 sur 5 jours", 3500.0, 1, null, null);
        String requestContent = objectMapper.writeValueAsString(testTraining);
        when(implTrainingService.saveTraining(testTraining)).thenReturn(testTraining);
        MvcResult result = (MvcResult) mockMvc.perform(MockMvcRequestBuilders.post("/api/trainings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isCreated())
                .andReturn();
    }
    @Test
    public void testTrainingsByCategory() throws Exception {
        Long categoryId = 1L;
        List<Training> trainings = Arrays.asList(
                new Training(1L, "Java", "Java Standard Edition 8 sur 5 jours", 3500.0, 1, "java.png", null),
                new Training(2L, "Spring", "Spring Framework sur 5 jours", 4000.0, 1, "spring.png", null)
        );

        when(implTrainingService.getTrainingsByCategory(categoryId)).thenReturn(trainings);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/trainings/category/{id}", categoryId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(trainings);
        String actualJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testDeleteTraining() throws Exception {
        Long trainingId = 1L;

        doNothing().when(implTrainingService).deleteTraining(trainingId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/trainings/{id}", trainingId))
                .andExpect(status().isNoContent());
    }
    @Test
    public void testGetTrainingByIdNotFound() throws Exception {
        Long trainingId = 1L;

        when(implTrainingService.readTraining(trainingId)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/trainings/{id}", trainingId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}





