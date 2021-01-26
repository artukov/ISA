package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.AbsenceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRequestRepository  extends JpaRepository<AbsenceRequest, Long> {
}
