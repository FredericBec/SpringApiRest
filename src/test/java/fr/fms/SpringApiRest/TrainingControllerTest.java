package fr.fms.SpringApiRest;

import fr.fms.SpringApiRest.dao.TrainingRepository;
import fr.fms.SpringApiRest.service.ImplTrainingService;
import fr.fms.SpringApiRest.web.TrainingController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TrainingController.class)
public class TrainingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainingRepository trainingRepository;

    @MockBean
    private ImplTrainingService implTrainingService;

    @Test
    public void testGetTrainings() throws Exception{
        mockMvc.perform(get("/api/trainings"))
                .andExpect(status().isOk());
    }
}
