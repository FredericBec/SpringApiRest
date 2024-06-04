package fr.fms.SpringApiRest.dao;

import fr.fms.SpringApiRest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
