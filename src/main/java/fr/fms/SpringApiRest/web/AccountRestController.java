package fr.fms.SpringApiRest.web;


import fr.fms.SpringApiRest.entities.AppRole;
import fr.fms.SpringApiRest.entities.AppUser;
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
    ResponseEntity<List<AppUser>> getUsers(){
        return this.accountService.listUser();
    }

    @PostMapping("/users")
    public AppUser postUser(@RequestBody AppUser user){
        return this.accountService.saveUser(user);
    }

    @PostMapping("/roles")
    public AppRole postRole(@RequestBody AppRole role){
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