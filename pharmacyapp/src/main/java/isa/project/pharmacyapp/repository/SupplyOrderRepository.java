package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.OrderStatus;
import isa.project.pharmacyapp.model.SupplyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {

    @Query(value = "SELECT * FROM supply_order so\n" +
            "WHERE pharmacy_id = :pharmacyID AND status =:status"
            ,nativeQuery = true)
    List<SupplyOrder> findByStatusPharmacyID(@Param("status")OrderStatus status, @Param("pharmacyID")Long pharmacyID);
}
