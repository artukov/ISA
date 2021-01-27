package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.AbsenceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceRequestRepository  extends JpaRepository<AbsenceRequest, Long> {
}
