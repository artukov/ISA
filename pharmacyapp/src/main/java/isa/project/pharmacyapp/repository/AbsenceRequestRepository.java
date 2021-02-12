package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.AbsenceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRequestRepository  extends JpaRepository<AbsenceRequest, Long> {


    @Query(value = "SELECT ar.* FROM absence_request ar\n" +
            "INNER JOIN pharmacist p ON p.id = ar.user_id \n" +
            "WHERE ar.pharmacy_id = :pharmacyID\n" +
            "AND ar.status IS NULL"
            ,nativeQuery = true)
    List<AbsenceRequest> findAllPharmacyPharmacistUnanswered(Long pharmacyID);

    @Query(value="SELECT ar.* FROM absence_request ar\n" +
            "INNER JOIN dermatologist d ON d.id = ar.user_id\n" +
            "WHERE ar.status IS NULL",nativeQuery = true)
    List<AbsenceRequest> findAllDermatologistsRequests();
}
