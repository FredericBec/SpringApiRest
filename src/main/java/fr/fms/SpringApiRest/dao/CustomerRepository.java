package fr.fms.SpringApiRest.dao;

import fr.fms.SpringApiRest.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
