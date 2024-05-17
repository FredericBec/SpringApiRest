package fr.fms.SpringApiRest;

import fr.fms.SpringApiRest.dao.CategoryRepository;
import fr.fms.SpringApiRest.dao.TrainingRepository;
import fr.fms.SpringApiRest.entities.Category;
import fr.fms.SpringApiRest.entities.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApiRestApplication implements CommandLineRunner {

	@Autowired
	TrainingRepository trainingRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringApiRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		generateData();
	}

	private void generateData()
	{

		Category program = new Category(null, "Devellopement Web", null);
		Category logiciel = new Category(null, "Logiciel", null);
		Category Cybersecu = new Category(null, "Cybersecurite", null);
		categoryRepository.save(program);
		categoryRepository.save(logiciel);
		categoryRepository.save(Cybersecu);
		trainingRepository.save(new Training(null, "Java", "Java Standard Edition 8 sur 5 jours", 3500.0, 1, "java.png", program));
		trainingRepository.save(new Training(null, "DotNet", "DotNet & entityframework en 5 jours", 2750.0, 1, "dotnet_logo_icon_170223.png", logiciel));
		trainingRepository.save(new Training(null, "PowerBi", "Business Intelligence 5 jours", 3000.0, 1, "Power-BI-Logo-700x394.png", logiciel));
		trainingRepository.save(new Training(null, "NodeJs", "Prise en main de NodeJs/Express 2 jours", 1400.0, 1, "node-js.png", program));
		trainingRepository.save(new Training(null, "Php", "Initiation au Dev/Web avec hp 4 jours", 1300.0, 1, "PHP-logo.svg.png",Cybersecu));
	}
}
