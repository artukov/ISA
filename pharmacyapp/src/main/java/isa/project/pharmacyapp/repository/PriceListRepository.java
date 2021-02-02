package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Long> {
    List<PriceList> findByPharmacy_id(Long pharmacyID);


    /**
     * TODO:
     * Create a ID generator for the price list and modify query with DESC instead of ASC
     * */
    @Query(value = "SELECT * FROM price_list pl " +
            "WHERE pl.end_date > now() AND (now() - pl.start_date) = (" +
            "SELECT MIN(now() - pl2.start_date) FROM price_list pl2 )" +
            " AND pl.pharmacy_id = :pharmacyID AND pl.active = true  " +
            "ORDER BY  pl.id ASC " +
            "LIMIT 1"
            ,nativeQuery = true)
    PriceList findLatest(@Param("pharmacyID") Long pharmacyID);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE  price_list pl SET pl.active = false " +
            "WHERE pl.id != :plID AND pl.pharmacy_id = :pharmacyID AND pl.active = true")
    void updateActiveStatus(@Param("plID")Long plID, @Param("pharmacyID") Long pharmacyID);

    @Query(value = "select pd.price\n" +
            "from price_list pl inner join pl_drug pd on pl.id = pd.pricelist_id\n" +
            "where pharmacy_id = :pharmacyId and pl.active = true and pd.drug_id = :drugId", nativeQuery = true)
    Double getDrugPriceByPharmacyPriceList(@Param("pharmacyId") Long pharmacyId, @Param("drugId") Long drugId);

}
