package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.DrugNotInPharmacyStashRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugNotInPharmacyStashRequestRepository  extends JpaRepository<DrugNotInPharmacyStashRequest, Long> {
}
