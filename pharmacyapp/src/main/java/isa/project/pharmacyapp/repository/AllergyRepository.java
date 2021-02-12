package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Address;
import isa.project.pharmacyapp.model.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {
}
