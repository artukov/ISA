package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {

    @Query(value = "SELECT d.* FROM drug d INNER JOIN pharmacy_drug pd ON d.id = pd.drug_id WHERE pd.pharmacy_id = :pharmacy_id",
            nativeQuery = true)
    public List<Drug> findAllByPharmacyId(@Param("pharmacy_id")Long id);

    @Modifying
    @Query(value = "DELETE FROM drug d WHERE d.id = :id",nativeQuery = true)
    public void deleteDrug(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM appointment_drug ad inner join  calendar_appointments ca on ad.appointment_id = ca.appointment_id " +
            "WHERE calendar_id = :id " +
            "AND EXTRACT(month FROM ca.appointment_date) = :month " +
            "AND EXTRACT(year FROM ca.appointment_date) = :year" ,
            nativeQuery = true)
    Double getMonthlyConsumptionStatistics(@Param("id") Long id,@Param("month") Integer month,@Param("year") Double year);


    @Query(value = "SELECT COUNT(*) FROM appointment_drug ad inner join  calendar_appointments ca on ad.appointment_id = ca.appointment_id\n" +
            "WHERE calendar_id = :id " +
            "AND EXTRACT(month FROM ca.appointment_date) BETWEEN  :month AND :month+3 " +
            "AND EXTRACT(year FROM ca.appointment_date) = :year"
            , nativeQuery = true)
    Double getQuarterlyDrugConsumptionStatistics(@Param("id") Long id,@Param("month") Integer month,@Param("year") Double year);

    @Query(value = "SELECT COUNT(*) FROM appointment_drug ad inner join calendar_appointments ca on ad.appointment_id = ca.appointment_id " +
            "WHERE ca.calendar_id = :id " +
            "GROUP BY EXTRACT(year FROM ca.appointment_date)"
            ,nativeQuery = true)
    List<Double> getYearlyDrugConsumptionStatistics(@Param("id") Long id);


    @Modifying
    @Query(value = "UPDATE pharmacy_drug " +
            "SET amount = :amount " +
            "WHERE pharmacy_id = :pharmacyID AND drug_id = :drugID "
            ,nativeQuery = true)
    void addDrugToPharmacy(@Param("drugID") Long drugID,@Param("pharmacyID") Long pharmacyID,@Param("amount") Integer amount);

    @Query(value = "SELECT COUNT(*) FROM pharmacy_drug WHERE drug_id = :drugID and pharmacy_id = :pharmacyID",nativeQuery = true)
    Double findInPharmacy(@Param("pharmacyID") Long pharmacyID, @Param("drugID") Long drugID);
}
