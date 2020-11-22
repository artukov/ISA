package isa.project.pharmacyapp.repository;

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
    public void deleteById(@Param("id") Long id);


}
