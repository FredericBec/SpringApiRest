package fr.fms.SpringApiRest.dao;

import fr.fms.SpringApiRest.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRolename(String rolename);
}
