package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}
