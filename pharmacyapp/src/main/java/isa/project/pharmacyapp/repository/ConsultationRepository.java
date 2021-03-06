package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Consultation;
import isa.project.pharmacyapp.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    @Query(nativeQuery = true, value = "select *\n" +
            "from consultation c \n" +
            "where c.patient_id = :patientId and c.beg_date = :dateTime and (c.finished != true OR c.finished is null)")
    Consultation findConsultation(@Param("patientId") Long patientId, @Param("dateTime") Date dateTime);

    @Query(nativeQuery = true, value = "select p.id\n" +
            "from consultation c  inner join calendar_appointments ca on c.id = ca.appointment_id inner join pharmacy p on ca.calendar_id = p.calendar_id\n" +
            "where c.id = :consultationId")
    Long getPharmacyIdByConsultation(@Param("consultationId") Long consultationId);

    @Query(value = "select c.*\n" +
            "from consultation c\n" +
            "where c.patient_id = :patientId and c.finished = true", nativeQuery = true)
    List<Consultation> getPatientsConsultations(@Param("patientId") Long patientId);

    @Query(value = "select c.*\n" +
            "from consultation c\n" +
            "where c.patient_id = :patientId and c.finished != true", nativeQuery = true)
    List<Consultation> getPatientsConsultationsNotFinished(@Param("patientId") Long patientId);

    @Query(value = "select c.beg_date, p.firstname, p.lastname, p.email\n" +
            "from consultation c inner join patient p on c.patient_id = p.id\n" +
            "where c.pharmacist_id = :pharmacistId", nativeQuery = true)
    List<Object[]> getPharmacistsConsultations(@Param("pharmacistId") Long pharmacistId);

    @Query(value = "select c.beg_date, p.firstname, p.lastname, p.email\n" +
            "from consultation c left outer join patient p on c.patient_id = p.id\n" +
            "where c.pharmacist_id = :pharmacistId and extract(month from c.beg_date) = :month and extract(year from c.beg_date) = :year", nativeQuery = true)
    List<Object[]> getPharmacistCalendar(@Param("pharmacistId") Long pharmacistId, @Param("month") Integer month, @Param("year") Integer year);
}
