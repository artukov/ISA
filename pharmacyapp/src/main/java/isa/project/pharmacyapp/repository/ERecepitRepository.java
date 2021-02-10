package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.EReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ERecepitRepository extends JpaRepository<EReceipt, Long> {

    @Query(value = "select *\n" +
            "from erecepit\n" +
            "where patient_id = :patientId", nativeQuery = true)
    List<EReceipt> findByPatientId(@Param("patientId") Long patientId);
}
