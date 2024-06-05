package fr.fms.SpringApiRest.web;


import fr.fms.SpringApiRest.entities.Role;
import fr.fms.SpringApiRest.entities.User;
import fr.fms.SpringApiRest.service.AccountServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class AccountRestController {

    @Autowired
    AccountServiceImpl accountService;

    @GetMapping("/users")
    ResponseEntity<List<User>> getUsers(){
        return this.accountService.listUser();
    }

    @PostMapping("/users")
    public User postUser(@RequestBody User user){
        return this.accountService.saveUser(user);
    }

    @PostMapping("/roles")
    public Role postRole(@RequestBody Role role){
        return this.accountService.saveRole(role);
    }

    @PostMapping("/roleUser")
    public void postRoleToUser(@RequestBody UserRoleForm userRoleForm){
        accountService.addRoleToUser(userRoleForm.getUsername(), userRoleForm.getRolename());
    }
}

@Data
class UserRoleForm{
    private String username;
    private String rolename;
}