package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Dermatologist;
import isa.project.pharmacyapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByEmail(String email);

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

    @Query(value = "select *\n" +
            "from patient\n" +
            "where firstname = :firstName and lastname = :lastName", nativeQuery = true)
    List<Patient> findPatientByNameAndSurname(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query(value = "select count(*)\n" +
            "from patient p inner join consultation c on p.id = c.patient_id\n" +
            "where (:startHour, :endHours) overlaps\n" +
            "(c.beg_date , c.beg_date + make_interval(hours => c.duration)) and p.id = :patientId", nativeQuery = true)
    Double overlappingConsultationHours(@Param("startHour") Date startHour, @Param("endHours") Date endHours, @Param("patientId") Long patientId);

    @Query(value = "select count(*)\n" +
            "from patient p inner join examination e on p.id = e.patient_id\n" +
            "where (:startHour, :endHours) overlaps\n" +
            "(e.beg_date , e.beg_date + make_interval(hours => e.duration)) and p.id = :patientId", nativeQuery = true)
    Double overlappingExaminationHours(@Param("startHour") Date startHour, @Param("endHours") Date endHours,@Param("patientId") Long patientId);

}
