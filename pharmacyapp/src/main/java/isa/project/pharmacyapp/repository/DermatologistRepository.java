package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DermatologistRepository  extends JpaRepository<Dermatologist, Long> {

    @Query(value = "SELECT d.* FROM dermatologist d INNER JOIN pharmacy_derma pd ON pd.derma_id = d.id WHERE pd.pharmacy_id = :pharmacyId",
            nativeQuery = true)
    List<Dermatologist> findAllByPharmacy(@Param("pharmacyId") Long pharmacyId);
}
