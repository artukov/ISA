package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query(value = "SELECT COUNT(*) FROM reservation INNER JOIN reservation_drug rd " +
            "ON reservation.id = rd.reservation_id " +
            "INNER JOIN  pharmacy_drug pd ON rd.drug_id = pd.drug_id " +
            "WHERE  (accepted = false  or accepted is null)" +
            "AND pd.drug_id = :drugID AND pd.pharmacy_id = :pharmacyID"
            , nativeQuery = true)
    double countReservedDrugsInPharmacy(@Param("drugID") Long drugID,@Param("pharmacyID") Long pharmacyID);
}
