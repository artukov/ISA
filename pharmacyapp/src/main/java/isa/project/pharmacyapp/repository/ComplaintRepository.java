package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Complaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaints, Long> {
    List<Complaints> findByAnswerIsNull();
}
