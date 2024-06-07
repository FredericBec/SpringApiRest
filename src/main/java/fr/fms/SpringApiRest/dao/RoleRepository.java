package fr.fms.SpringApiRest.dao;

import fr.fms.SpringApiRest.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRolename(String rolename);
}
