package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(value = "select p.*\n" +
            "from examination e inner join patient p on e.patient_id = p.id\n" +
            "where e.derma_id = :dermaId\n" +
            "order by :orderCondition", nativeQuery = true)
    List<Patient> findDermatologistPatients(@Param("dermaId") Long dermaId, @Param("orderCondition") String orderCondition);

    @Query (value = "select p.*\n" +
            "from consultation c inner join patient p on c.patient_id = p.id\n" +
            "where c.pharmacist_id = :pharmaId\n" +
            "order by :orderCondition", nativeQuery = true)
    List<Patient> findPharmacistPatients(@Param("pharmaId") Long pharmaId, @Param("orderCondition") String orderCondition);
}
