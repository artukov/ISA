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


    @Query(value = "SELECT COUNT(appointment_date) FROM calendar_appointments " +
            "INNER JOIN examination e on calendar_appointments.appointment_id = e.id \n" +
            "WHERE calendar_id = :id  AND e.finished = true \n" +
            "AND EXTRACT(month FROM appointment_date) = :month " +
            "AND EXTRACT(year FROM appointment_date) = :year"
            ,nativeQuery = true)
    Double getMonthlyExaminations(@Param("id") Long id,@Param("month") int month, @Param("year") Double year );



    @Query(value = "SELECT COUNT(appointment_date) from calendar_appointments  " +
            "INNER JOIN examination e on calendar_appointments.appointment_id = e.id \n" +
            "WHERE calendar_id = :id AND e.finished = true \n" +
            "AND " +
            "EXTRACT(month FROM appointment_date) BETWEEN  :month AND :month+3 " +
            "AND " +
            "EXTRACT(year FROM appointment_date) = :year",
            nativeQuery = true)
    Double getQuarterlyExaminations(@Param("id") Long id,@Param("month") int month, @Param("year") Double year);


    @Query(value = "SELECT COUNT( appointment_date)  FROM calendar_appointments " +
            "INNER JOIN examination e on calendar_appointments.appointment_id = e.id \n" +
            "WHERE calendar_id = :id AND e.finished = true \n " +
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

    @Query(value = "select c.*, p.id\n" +
            "from pharmacist p inner join pharmacy ph\n" +
            "    on p.pharmacy_id = ph.id\n" +
            "    inner join calendar_appointments c on ph.calendar_id = c.calendar_id inner join consultation con on c.appointment_id = con.id\n" +
            "where p.id = :pharmaId ",nativeQuery = true)
    List<Object[]> getPharmacistCalendar(@Param("pharmaId") Long pharmaId);

    @Query(value = "select c.*, ph.id\n" +
            "from pharmacy_derma pd inner join pharmacy ph on pd.pharmacy_id = ph.id inner join calendar_appointments c on ph.calendar_id = c.calendar_id inner join examination e on e.id = c.appointment_id\n" +
            "where pd.derma_id = :dermaId", nativeQuery = true)
    List<Object[]> getDermatologistCalendar(@Param("dermaId") Long dermaId);


    @Query(value ="SELECT COUNT(*) FROM calendar_appointments " +
            "WHERE calendar_id = :calendarID and appointment_id = :appointmentID"
            ,nativeQuery = true)
    Double appointmentAlreadyExists(@Param("calendarID")Long calendarID,@Param("appointmentID") Long appointmentID);

    @Query(value = "SELECT SUM(COALESCE(con.price,0)) + SUM(COALESCE(e.price,0)) AS SUM FROM calendar_appointments ca\n" +
            "INNER JOIN calendar c ON c.id = ca.calendar_id\n" +
            "INNER JOIN pharmacy p ON c.id = p.calendar_id\n" +
            "LEFT OUTER JOIN examination e ON e.id = ca.appointment_id\n" +
            "LEFT OUTER JOIN consultation con ON con.id = ca.appointment_id\n" +
            "WHERE p.id= :pharmacyID AND  \n" +
            "(( e.finished = true AND EXTRACT(month from  e.beg_date) = :month AND EXTRACT(year FROM e.beg_date) = :year)\n" +
            "OR " +
            "( con.finished = true AND EXTRACT(month from con.beg_date)= :month AND EXTRACT(year FROM con.beg_date) = :year ));"
            ,nativeQuery = true)
    Double getAppointmentsPriceBasedOnDate(@Param("month")Integer month, @Param("year") Integer year,
                                           @Param("pharmacyID") Long pharmacyID);
}
