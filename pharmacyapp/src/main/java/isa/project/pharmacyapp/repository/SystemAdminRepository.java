package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemAdminRepository extends JpaRepository<SystemAdmin, Long> {
    SystemAdmin findByEmail(String email);
}
