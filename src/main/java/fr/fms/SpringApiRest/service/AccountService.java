package fr.fms.SpringApiRest.service;

import fr.fms.SpringApiRest.entities.Role;
import fr.fms.SpringApiRest.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String rolename);
    User findUserByUsername(String username);
    ResponseEntity<List<User>> listUser();
}
