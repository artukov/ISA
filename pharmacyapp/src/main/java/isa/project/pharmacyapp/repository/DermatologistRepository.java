package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Dermatologist;
import isa.project.pharmacyapp.model.User;
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

    Dermatologist findByEmail(String email);

    @Query(value = "SELECT AVG(drr.ratings) FROM  derma_ratings dr inner join " +
            "dermatologist_ratings_ratings drr on dr.derma_id = drr.dermatologist_ratings_derma_id " +
            "and dr.pharmacy_id = drr.dermatologist_ratings_pharmacy_id " +
            "WHERE dr.derma_id = :dermaID AND dr.pharmacy_id = :pharmacyID",
            nativeQuery = true)
    Double getAvgRatings(@Param("dermaID") Long dermaID, @Param("pharmacyID") Long pharmacyID);
}
