package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Address;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.model.many2many.PharmacyDermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

    @Modifying
    @Query(value = "DELETE FROM pharmacy where id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);

    @Query(value = "SELECT avg(pr.ratings) FROM pharmacy_ratings pr WHERE pr.pharmacy_id= :pharmacyId"
            ,nativeQuery = true)
    Double getAvgRating(@Param("pharmacyId") Long pharmacyId);


    @Query(value = "SELECT a.* FROM address a " +
            " INNER JOIN pharmacy p on a.id = p.address_id \n" +
            "WHERE p.id = :pharmacyID",nativeQuery = true)
    Address getAddress(@Param("pharmacyID") Long pharmacyID);

    @Query(value = "SELECT count(*) FROM pharmacy_derma pd\n" +
            "WHERE ( :startHour , :endHours) OVERLAPS\n" +
            "(pd.start_hour , pd.start_hour + make_interval(hours => pd.hours) ) \n" +
            "AND pd.derma_id = :dermaID AND pd.pharmacy_id != :pharmacyID \n",nativeQuery = true)
    Double overlappingWorkingHours(@Param("dermaID") Long dermaID,@Param("pharmacyID") Long pharmacyID,
                                   @Param("startHour") Date startHour,@Param("endHours") Date endHours);

    @Query(value = "select *\n" +
            "from pharmacy_derma\n" +
            "where derma_id = :dermaId", nativeQuery = true)
    List<Object[]> getDermaPharmacy(@Param("dermaId") Long dermaId);

    @Query(value = "select p.*\n" +
            "from pharmacy p inner join pharmacist ps on p.id = ps.pharmacy_id inner join consultation c on ps.id = c.pharmacist_id\n" +
            "where c.beg_date != :dateTime", nativeQuery = true)
    List<Pharmacy> getPharmaciesWithFreeConsultation(@Param("dateTime") Date dateTime);
}
