package fr.fms.SpringApiRest;

import fr.fms.SpringApiRest.dao.TrainingRepository;
import fr.fms.SpringApiRest.entities.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApiRestApplication implements CommandLineRunner {

	@Autowired
	TrainingRepository trainingRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringApiRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		generateData();
	}

	private void generateData(){
		trainingRepository.save(new Training(null, "Java", "Java Standard Edition 8 sur 5 jours", 3500.0, 1));
		trainingRepository.save(new Training(null, "DotNet", "DotNet & entityframework en 5 jours", 2750.0, 1));
		trainingRepository.save(new Training(null, "PowerBi", "Business Intelligence 5 jours", 3000.0, 1));
		trainingRepository.save(new Training(null, "NodeJs", "Prise en main de NodeJs/Express 2 jours", 1400.0, 1));
		trainingRepository.save(new Training(null, "Php", "Initiation au Dev/Web avec hp 4 jours", 1300.0, 1));
	}
}