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

    @Query(value = "SELECT DISTINCT so.* FROM supply_order so\n " +
            "INNER JOIN pharmacy_admin pa ON so.admin_id = pa.id \n" +
            "INNER JOIN supplier_order s ON so.id = s.order_id \n" +
            "WHERE pa.pharmacy_id = :pharmacyID AND s.status =:status"
            ,nativeQuery = true)
    List<SupplyOrder> findByStatusPharmacy(@Param("status")Integer status, @Param("pharmacyID")Long pharmacyID);


    @Modifying
    @Query(value = "UPDATE supplier_order \n" +
            "SET price_offer = :priceOffer , delivery_date = :deliveryDate \n" +
            "WHERE supplier_id = :supplierID AND order_id = :orderID"
            , nativeQuery = true)
    void updateOffer(@Param("orderID")Long orderID, @Param("supplierID") Long id1,
                     @Param("priceOffer") Double priceOffer,@Param("deliveryDate") Date deliveryDate);


    @Query(value = "SELECT so.* FROM supply_order so\n" +
            "            INNER JOIN supplier_order s ON so.id = s.order_id\n" +
            "            WHERE (s.delivery_date IS NULL  OR s.price_offer IS NULL)" +
            "            AND so.admin_id = :adminID\n" +
            "GROUP BY so.id\n" +
            "HAVING (COUNT(so.id) = (\n" +
            "    SELECT COUNT(*) FROM\n" +
            "        supplier_order s1\n" +
            "        WHERE s1.order_id = so.id\n" +
            "    ));\n" +
            ""
            , nativeQuery = true)
    List<SupplyOrder> findWithoutOffers(@Param("adminID")Long adminID);


    @Modifying
    @Query(value ="DELETE FROM supplier_order WHERE order_id = :orderID",nativeQuery = true)
    void deleteSuppliers(@Param("orderID")Long orderID);

    @Modifying
    @Query(value = "DELETE FROM supply_drug WHERE supply_id = :orderID",nativeQuery = true)
    void deleteDrugs(@Param("orderID")Long orderID);
    @Modifying
    @Query(value = "DELETE FROM supply_order WHERE id = :orderID",nativeQuery = true)
    void deleteOrder(@Param("orderID")Long orderID);

    @Modifying
    @Query(value ="DELETE FROM supplier_order WHERE order_id = :orderID AND supplier_id = :supplierID",nativeQuery = true)
    void deleteOrderSuppliers(@Param("orderID")Long orderID, @Param("supplierID")Long supplierID);

    @Modifying
    @Query(value = "DELETE FROM supply_drug WHERE supply_id = :orderID AND drug_id = :drugID",nativeQuery = true)
    void deleteOrderDrugs(@Param("orderID")Long orderID, @Param("drugID")Long drugID);

    @Query(value = "SELECT supplier_id FROM supplier_order WHERE order_id = :orderID "
            ,nativeQuery = true)
    List<Long> findAllSupplierOrderIds(@Param("orderID")Long orderID);


    @Query(value ="SELECT * FROM supply_order\n" +
            "INNER JOIN supplier_order so on supply_order.id = so.order_id\n" +
            "WHERE supplier_id = :supplierID\n" +
            "AND (so.delivery_date IS NULL OR so.price_offer IS NULL) "
            ,nativeQuery = true)
    List<SupplyOrder> findSupplierIncoming(@Param("supplierID")Long supplierID);

    @Query(value = "SELECT * FROM supply_order " +
            "INNER JOIN supplier_order so on supply_order.id = so.order_id\n" +
            "WHERE so.supplier_id = :supplierID\n" +
            "ORDER BY id ASC" ,nativeQuery = true)
    List<SupplyOrder> findSupplierOrders(@Param("supplierID")Long supplierID);
}
