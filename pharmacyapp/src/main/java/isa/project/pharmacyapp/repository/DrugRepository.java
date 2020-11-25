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
}
