package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Patient;
import isa.project.pharmacyapp.model.Pharmacist;
import isa.project.pharmacyapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {

    @Query(value = "SELECT * FROM pharmacist p WHERE p.pharmacy_id = :pharmacyId", nativeQuery = true)
    List<Pharmacist> findAllByPharmacy(@Param("pharmacyId") Long pharmacyId);

    Pharmacist findByEmail(String email);

    @Query(value = "SELECT AVG(pr.ratings) FROM pharmacist_ratings pr WHERE pharmacist_id = :id "
            ,nativeQuery = true)
    Double getAvgRatings(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM consultation c INNER JOIN pharmacist p ON c.pharmacist_id = p.id " +
            "WHERE c.finished  = false " +
            "AND p.id = :id"
            ,nativeQuery = true)
    double existsUnfinishedConsultation(@Param("id")Long id);

    @Modifying
    @Query(value = "UPDATE pharmacist " +
            "SET pharmacy_id = null " +
            "WHERE id = :id"
            , nativeQuery = true)
    void deletePharmacistFromPharmacy(@Param("id")Long id);

    @Query(value ="select count(*)\n" +
            "from pharmacist p\n" +
            "where (:startHour, :endHours) overlaps\n" +
            "      (p.start_hour , p.start_hour + make_interval(hours => p.hours)) and p.id = :pharmacistId", nativeQuery = true)
    Double overlappingWorkingHours(@Param("startHour") Date startHour, @Param("endHours") Date endHours,@Param("pharmacistId") Long pharmacistId);

}
