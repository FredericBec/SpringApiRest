package fr.fms.SpringApiRest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@AutoConfigureMockMvc
class SpringApiRestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		int value1 = 1;
		int value2 = 2;
		assertNotEquals(value1, value2);
	}

	@Test
	void testGetTrainingAndTestName() throws Exception
	{
		mockMvc.perform(get("/api/trainings"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("Java")));
	}
}
