package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
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
            " AND pl.pharmacy_id = :pharmacyID " +
            "ORDER BY  pl.id ASC " +
            "LIMIT 1"
            ,nativeQuery = true)
    PriceList findLatest(@Param("pharmacyID") Long pharmacyID);
}
