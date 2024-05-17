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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
    public void testGetTrainings() throws Exception{
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
}



