package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.DrugNotInPharmacyStashRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugNotInPharmacyStashRequestRepository  extends JpaRepository<DrugNotInPharmacyStashRequest, Long> {
    List<DrugNotInPharmacyStashRequest> findByPharmacy_id(Long pharmacyID);
}
