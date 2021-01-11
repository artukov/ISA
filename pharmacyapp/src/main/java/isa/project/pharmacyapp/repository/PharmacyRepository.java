package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Address;
import isa.project.pharmacyapp.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
