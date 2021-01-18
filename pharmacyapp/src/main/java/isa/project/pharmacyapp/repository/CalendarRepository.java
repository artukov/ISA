package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Appointment;
import isa.project.pharmacyapp.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    @Query(value = "SELECT appointment_date FROM calendar_appointments WHERE calendar_id = :id ",
            nativeQuery = true)
    List<Timestamp> getDateExaminations(Long id);


    @Query(value = "SELECT COUNT(appointment_date) FROM calendar_appointments WHERE calendar_id = :id" +
            " AND EXTRACT(month FROM appointment_date) = :month " +
            "AND EXTRACT(year FROM appointment_date) = :year"
            ,nativeQuery = true)
    Double getMonthlyExaminations(@Param("id") Long id,@Param("month") int month, @Param("year") Double year );



    @Query(value = "SELECT COUNT(appointment_date) from calendar_appointments " +
            "WHERE calendar_id = :id " +
            "AND " +
            "EXTRACT(month FROM appointment_date) BETWEEN  :month AND :month+3 " +
            "AND " +
            "EXTRACT(year FROM appointment_date) = :year",
            nativeQuery = true)
    Double getQuarterlyExaminations(@Param("id") Long id,@Param("month") int month, @Param("year") Double year);


    @Query(value = "SELECT COUNT( appointment_date)  FROM calendar_appointments " +
            "WHERE calendar_id = 200 " +
            "GROUP BY EXTRACT(year FROM appointment_date)", nativeQuery = true)
    List<Double> getYearlyExaminations(@Param("id") Long id);

    @Query(value = "SELECT EXTRACT(year FROM appointment_date)  FROM calendar_appointments " +
            "WHERE calendar_id = :id " +
            "GROUP BY EXTRACT(year FROM appointment_date);"
            ,nativeQuery = true)
    List<Double> getAllYears(@Param("id") Long id);


    @Query(value = "SELECT sum(e.price) FROM  examination e " +
            "inner join calendar_appointments ca on e.id = ca.appointment_id " +
            "WHERE ca.appointment_date between :lowerLimit and :upperLimit AND ca.calendar_id = :id"
            ,nativeQuery = true)
    Double getAppointmentsBasedOnDate(@Param("id") Long id,@Param("lowerLimit") Date lowerLimit,@Param("upperLimit")  Date upperLimit);

    @Query(value = "select c.*\n" +
            "from pharmacist p inner join pharmacy ph\n" +
            "    on p.pharmacy_id = ph.id\n" +
            "    inner join calendar_appointments c on ph.calendar_id = c.calendar_id inner join consultation con on c.appointment_id = con.id\n" +
            "where p.id = :pharmaId ",nativeQuery = true)
    List<Object[]> getPharmacistCalendar(@Param("pharmaId") Long pharmaId);
}
