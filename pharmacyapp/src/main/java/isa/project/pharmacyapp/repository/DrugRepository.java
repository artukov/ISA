package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.dto.PharmaDrugDTO;
import isa.project.pharmacyapp.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {

    @Query(value = "SELECT d.* FROM drug d INNER JOIN pharmacy_drug pd ON d.id = pd.drug_id WHERE pd.pharmacy_id = :pharmacy_id " +
            "ORDER BY d.id",
            nativeQuery = true)
    List<Drug> findAllByPharmacyId(@Param("pharmacy_id")Long id);

    @Modifying
    @Query(value = "DELETE FROM pharmacy_drug WHERE drug_id = :id"
            ,nativeQuery = true)
    void deleteDrug(@Param("id") Long id);

//    @Query(value = "SELECT COUNT(*) FROM appointment_drug ad inner join  calendar_appointments ca on ad.appointment_id = ca.appointment_id " +
//            "WHERE calendar_id = :id " +
//            "AND EXTRACT(month FROM ca.appointment_date) = :month " +
//            "AND EXTRACT(year FROM ca.appointment_date) = :year" ,
//            nativeQuery = true)
    @Query(value = "SELECT COUNT(*) FROM reservation_drug rd\n" +
            "INNER JOIN  reservation r ON rd.reservation_id = r.id\n" +
            "INNER JOIN pharmacy p ON p.id = r.pharmacy_id\n" +
            "WHERE p.id = :id AND r.accepted = true \n" +
            "AND EXTRACT(month FROM r.acceptance_date) = :month " +
            "AND EXTRACT(year FROM r.acceptance_date) = :year" ,
            nativeQuery = true)
    Double getMonthlyConsumptionStatistics(@Param("id") Long id,@Param("month") Integer month,@Param("year") Double year);


    @Query(value = "SELECT COUNT(*) FROM reservation_drug rd\n" +
            "INNER JOIN  reservation r ON rd.reservation_id = r.id\n" +
            "INNER JOIN pharmacy p ON p.id = r.pharmacy_id\n" +
            "WHERE p.id = :id AND r.accepted = true \n" +
            "AND EXTRACT(month FROM r.acceptance_date) BETWEEN  :month AND :month+3 " +
            "AND EXTRACT(year FROM r.acceptance_date) = :year"
            , nativeQuery = true)
    Double getQuarterlyDrugConsumptionStatistics(@Param("id") Long id,@Param("month") Integer month,@Param("year") Double year);

//    @Query(value = "SELECT COUNT(*) FROM appointment_drug ad inner join calendar_appointments ca on ad.appointment_id = ca.appointment_id " +
//            "WHERE ca.calendar_id = :id " +
//            "GROUP BY EXTRACT(year FROM ca.appointment_date)"
//            ,nativeQuery = true)
    @Query(value = "SELECT COUNT(*) FROM reservation_drug rd\n" +
            "INNER JOIN  reservation r ON rd.reservation_id = r.id\n" +
            "INNER JOIN pharmacy p ON p.id = r.pharmacy_id\n" +
            "WHERE p.id = :id AND r.accepted = true\n" +
            "AND  EXTRACT(year from r.acceptance_date) = :year"
            ,nativeQuery = true)
    Double getYearlyDrugConsumptionStatistics(@Param("id") Long id, @Param("year") Double year);

    @Query(value = "select  distinct p.pharmacy_id,pl.price\n" +
            "from drug d inner join pharmacy_drug pd\n" +
            "    on d.id = pd.drug_id inner join pl_drug pl on d.id = pl.drug_id inner join price_list p on pl.pricelist_id = p.id\n" +
            "where d.name = :drugName and  pd.amount != 0 and p.active = true",nativeQuery = true)
    List<Object[]> searchDrugs(@Param("drugName") String drugName);

    @Query(value = "select d.id, d.code,d.manufacturer,d.name,d.shape, d.type,ds.composition,ds.recom_consum,ds.side_effects\n" +
            "from drug d inner join drug_spec ds on d.spec_id = ds.id\n" +
            "where d.name = :drugName", nativeQuery = true)
    List<Object[]> getDrugSpec(@Param("drugName") String drugName);


    @Modifying
    @Query(value = "UPDATE pharmacy_drug " +
            "SET amount = amount + :amount " +
            "WHERE pharmacy_id = :pharmacyID AND drug_id = :drugID "
            ,nativeQuery = true)
    void addDrugToPharmacy(@Param("drugID") Long drugID,@Param("pharmacyID") Long pharmacyID,@Param("amount") Integer amount);

    @Query(value = "SELECT COUNT(*) FROM pharmacy_drug WHERE drug_id = :drugID and pharmacy_id = :pharmacyID",nativeQuery = true)
    Double findInPharmacy(@Param("pharmacyID") Long pharmacyID, @Param("drugID") Long drugID);

    @Query(value = "SELECT amount FROM pharmacy_drug WHERE drug_id = :drugID AND pharmacy_id = :pharmacyID",nativeQuery = true)
    Integer getAmount(@Param("drugID") Long drugID, @Param("pharmacyID") Long pharmacyID);


    @Query(value = "SELECT * FROM drug d\n" +
            "WHERE NOT EXISTS(SELECT * FROM pharmacy_drug pd\n" +
            "WHERE drug_id = d.id AND pharmacy_id = :pharmacyID )"
            ,nativeQuery = true)
    List<Drug> findAllNoInPharmacy(@Param("pharmacyID") Long pharmacyID);

    @Query(value = "select sd.substitute_id\n" +
            "from drug d inner join substitute_drugs sd on d.id = sd.original_id\n" +
            "where d.id = :drugId", nativeQuery = true)
    List<Long> findSubstituteDrug(@Param("drugId") Long drugId);

    @Query(value = "select count(*)\n" +
            "from allergy a inner join allergy_drug ad on a.id = ad.allergy_id\n" +
            "where a.patient_id = :patientId and ad.drug_id = :drugId", nativeQuery = true)
    Double getAllergy(@Param("patientId") Long patientId, @Param("drugId") Long drugId);

    @Query(value = "select drug_id\n" +
            "from drug d inner join recepit_drug rd on d.id = rd.drug_id\n" +
            "where rd.recepit_id = :receiptId", nativeQuery = true)
    List<Long> getDrugsFromEReceipt(@Param("receiptId") Long receiptId);

    @Query(value = "select pharmacy_id\n" +
            "from pharmacy_drug\n" +
            "where drug_id = :drugId", nativeQuery = true)
    List<Long> getPharmaciesWithDrug(@Param("drugId") Long drugId);

    @Query(value = "select drug_id\n" +
            "from pharmacy_drug\n" +
            "where pharmacy_id = :pharmacyId", nativeQuery = true)
    List<Long> getDrugsIdByPharmacy(@Param("pharmacyId") Long pharmacyId);

    @Query(value = "select d.*\n" +
            "from allergy a inner join allergy_drug ad on a.id = ad.allergy_id inner join substitute_drugs sd on ad.drug_id = sd.original_id inner join drug d on sd.substitute_id = d.id\n" +
            "where a.patient_id = :patientId and sd.original_id = :drugId", nativeQuery = true)
    List<Drug> checkForAllergy(@Param("patientId") Long patientId, @Param("drugId") Long drugId);

    //dodaj ovo na kraj queruya
    //and r.acceptance_date < now() - interval '1 day'
    @Query(value = "select d.*\n" +
            "from reservation r inner join reservation_drug rd on r.id = rd.reservation_id inner join drug d on rd.drug_id = d.id\n" +
            "where r.patient_id = :patientId and r.acceptance_date < now() - interval '1 day'", nativeQuery = true)
    List<Drug> getPatientsDrugsFromReservation(Long patientId);

    @Query(value = "select d.*\n" +
            "from erecepit e inner join recepit_drug rd on e.id = rd.recepit_id inner join drug d on rd.drug_id = d.id\n" +
            "where e.patient_id = :patientId", nativeQuery = true)
    List<Drug> getPatientDrugsFromEReceipt(@Param("patientId") Long patientId);
    @Query(value = "SELECT  SUM(COALESCE(pd.price,0)) FROM reservation r\n" +
            "INNER JOIN reservation_drug rd on r.id = rd.reservation_id\n" +
            "INNER JOIN drug d on d.id = rd.drug_id\n" +
            "INNER JOIN pl_drug pd on d.id = pd.drug_id\n" +
            "INNER JOIN price_list pl on pd.pricelist_id = pl.id\n" +
            "WHERE r.accepted = true AND r.pharmacy_id = :pharmacyID\n" +
            "  AND (EXTRACT(month FROM r.acceptance_date) =  :month " +
            "AND EXTRACT(year FROM r.acceptance_date) = :year )\n" +
            "AND pl.id =  (\n" +
            "    SELECT MAX(pl2.id) FROM price_list pl2\n" +
            "    WHERE ( :date BETWEEN pl2.start_date AND pl2.end_date)\n" +
            "    );"
            ,nativeQuery = true)
    Double getAcceptedReservationPrice(@Param("date") Timestamp date, @Param("month")Integer month, @Param("year") Integer year
            , @Param("pharmacyID") Long pharmacyID);
}
