package fr.fms.SpringApiRest;

import fr.fms.SpringApiRest.dao.CategoryRepository;
import fr.fms.SpringApiRest.dao.TrainingRepository;
import fr.fms.SpringApiRest.entities.Category;
import fr.fms.SpringApiRest.entities.AppRole;
import fr.fms.SpringApiRest.entities.Training;
import fr.fms.SpringApiRest.entities.AppUser;
import fr.fms.SpringApiRest.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class SpringApiRestApplication implements CommandLineRunner {

	@Autowired
	TrainingRepository trainingRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AccountServiceImpl accountService;

	public static void main(String[] args) {
		SpringApplication.run(SpringApiRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//generateData();
		//generateUserRoles();
	}

	private void generateData()
	{
		Category program = categoryRepository.save(new Category(null, "Developement Web", null));
		Category logiciel = categoryRepository.save(new Category(null, "Logiciel", null));
		Category Cybersecu = categoryRepository.save(new Category(null, "Cybersecurite", null));

		trainingRepository.save(new Training(null, "Java", "Java Standard Edition 8 sur 5 jours", 3500.0, 1, false,true , 10 ,"java.png" ,   program));
		trainingRepository.save(new Training(null, "DotNet", "DotNet & entityframework en 5 jours", 2750.0, 1,  false,true , 12 ,"dotnet.png" ,logiciel));
		trainingRepository.save(new Training(null, "PowerBi", "Business Intelligence 5 jours", 3000.0, 1, false,true , 18 ,"default.png" , logiciel));
		trainingRepository.save(new Training(null, "NodeJs", "Prise en main de NodeJs/Express 2 jours", 1400.0, 1, false,true , 8 ,"node.png"  , program));
		trainingRepository.save(new Training(null, "Php", "Initiation au Dev/Web avec hp 4 jours", 1300.0, 1, false,true , 20 ,"php.png"  , program));
		trainingRepository.save(new Training(null, "Javascript", "Java Standard Edition 8 sur 5 jours", 5500.0, 1, false,true , 15 ,"javascript.png" , program));
		trainingRepository.save(new Training(null, "Rust", "DotNet & entityframework en 5 jours", 2150.0, 1,  false,true , 6 ,"default.png" , logiciel));
		trainingRepository.save(new Training(null, "Swift", "Business Intelligence 5 jours", 3010.0, 1, false,true , 16 ,"swift.png"  , logiciel));
		trainingRepository.save(new Training(null, "API", "Prise en main de NodeJs/Express 2 jours", 1420.0, 1, false,true , 10 ,"default.png"  , Cybersecu));
		trainingRepository.save(new Training(null, "Spring", "Initiation au Dev/Web avec hp 4 jours", 1350.0, 1, false,true , 11 ,"default.png"   , Cybersecu));
	}

	private void generateUserRoles(){
		accountService.saveUser(new AppUser(null, "fred2024", "fmsacademy", new ArrayList<>()));
		accountService.saveUser(new AppUser(null, "martinmatin", "supermartin", new ArrayList<>()));
		accountService.saveRole(new AppRole(null, "ADMIN"));
		accountService.saveRole(new AppRole(null, "USER"));
		accountService.addRoleToUser("fred2024", "ADMIN");
		accountService.addRoleToUser("fred2024", "USER");
		accountService.addRoleToUser("martinmatin", "USER");
	}
}
