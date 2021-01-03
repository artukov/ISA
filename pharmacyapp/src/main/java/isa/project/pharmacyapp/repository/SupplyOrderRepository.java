package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.OrderStatus;
import isa.project.pharmacyapp.model.SupplyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {

    @Query(value = "SELECT * FROM supply_order so\n " +
            "INNER JOIN pharmacy_admin pa ON so.admin_id = pa.id \n" +
            "WHERE pa.pharmacy_id = :pharmacyID AND so.status =:status"
            ,nativeQuery = true)
    List<SupplyOrder> findByStatusPharmacyID(@Param("status")OrderStatus status, @Param("pharmacyID")Long pharmacyID);


    @Modifying
    @Query(value = "UPDATE supplier_order \n" +
            "SET price_offer = :priceOffer , delivery_date = :deliveryDate \n" +
            "WHERE supplier_id = :supplierID AND order_id = :orderID"
            , nativeQuery = true)
    void updateOffer(@Param("orderID")Long orderID, @Param("supplierID") Long id1,
                     @Param("priceOffer") Double priceOffer,@Param("deliveryDate") Date deliveryDate);
}
