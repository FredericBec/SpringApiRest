package fr.fms.SpringApiRest.dao;

import fr.fms.SpringApiRest.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
