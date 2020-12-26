package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {


    @Query(value = "SELECT e.* FROM examination e inner join dermatologist d on e.derma_id = d.id " +
            "            inner join pharmacy_derma pd on d.id = pd.derma_id " +
            "            WHERE d.id = :dermaID AND pd.pharmacy_id = :pharmacyID " +
            "            AND  (e.patient_id = 0 OR e.patient_id IS NULL)"
            ,nativeQuery = true)
    List<Examination> findByDermaPharmacy(@Param("dermaID")Long dermaID,@Param("pharmacyID") Long pharmacyID);
}
