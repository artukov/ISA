package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    @Query(value = "SELECT  count(*) FROM examination e INNER JOIN dermatologist d on d.id = e.derma_id\n" +
            "WHERE (:begDateTime BETWEEN e.beg_date AND (e.beg_date +  make_interval(mins => e.duration) ) \n" +
            " OR e.beg_date BETWEEN :begDateTime AND :endDateTime )\n" +
            "  AND (finished is null or finished = false) AND d.id = :dermaID",nativeQuery = true)
    double overlappingExaminations(@Param("begDateTime")Date begDateTime,@Param("endDateTime") Date endDateTime,@Param("dermaID") Long dermatologist_id);

//    List<Examination> findByDerma_idAndPatient_id(Long dermaId, Long patientId);

    @Query(nativeQuery = true, value = "SELECT * FROM examination e " +
            " WHERE e.derma_id = :dermaId AND e.patient_id = :patientId AND e.finished = true")
    List<Examination> findByDermaIdAndPatientId(@Param("dermaId")Long dermaId,@Param("patientId") Long patientId);

    @Query(nativeQuery = true, value = "select *\n" +
            "from examination e \n" +
            "where e.patient_id = :patientId and e.beg_date = :dateTime and (e.finished != true OR e.finished is null)")
    Examination findExamination(@Param("patientId") Long patientId, @Param("dateTime") Date dateTime);

    @Query(nativeQuery = true, value = "select p.id\n" +
            "from examination e  inner join calendar_appointments ca on e.id = ca.appointment_id inner join pharmacy p on ca.calendar_id = p.calendar_id\n" +
            "where e.id = :examinationId")
    Long getPharmacyIdByExamination(@Param("examinationId") Long examinationId);

    @Query(nativeQuery = true, value = "select e.beg_date, e.duration, e.price\n" +
            "from examination e\n" +
            "where e.patient_id = :patientId and e.finished = true")
    List<Examination> getPatientsDermaAppointments(@Param("patientId") Long patientId);
}
