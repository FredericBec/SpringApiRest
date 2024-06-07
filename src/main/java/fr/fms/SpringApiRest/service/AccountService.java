package fr.fms.SpringApiRest.service;

import fr.fms.SpringApiRest.entities.AppRole;
import fr.fms.SpringApiRest.entities.AppUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    AppUser saveUser(AppUser user);
    AppRole saveRole(AppRole role);
    void addRoleToUser(String username, String rolename);
    AppUser findUserByUsername(String username);
    ResponseEntity<List<AppUser>> listUser();
}
