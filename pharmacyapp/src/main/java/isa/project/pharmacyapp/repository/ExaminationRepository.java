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


    @Query(value = "SELECT e.* FROM examination e\n " +
            "INNER JOIN calendar_appointments ca ON e.id = ca.appointment_id\n" +
            "INNER JOIN pharmacy p ON ca.calendar_id = p.calendar_id\n" +
            "WHERE p.id = :pharmacyID AND ( (e.patient_id IS NULL OR e.patient_id = 0) AND e.finished = false );"
            ,nativeQuery = true)
    List<Examination> findByPharmacy(@Param("pharmacyID")Long pharmacyID);

    @Query(value = "select c.*\n" +
            "from pharmacist p inner join pharmacy ph\n" +
            "    on p.pharmacy_id = ph.id\n" +
            "    inner join calendar_appointments c on ph.calendar_id = c.calendar_id inner join consultation con on c.appointment_id = con.id\n" +
            "where p.id = :pharmaId ",nativeQuery = true)
    List<Object[]> getPharmacistCalendar(@Param("pharmaId") Long pharmaId);
}
