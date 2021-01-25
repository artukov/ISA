package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHouse, Long> {

    @Query(value = "SELECT amount FROM warehouse_drug wd INNER JOIN  warehouse w ON wd.warehouse_id = w.id \n" +
            "INNER JOIN  supplier s ON w.supplier_id = s.id \n" +
            "WHERE wd.drug_id = :drugID AND s.id = :supplierID"
            ,nativeQuery = true)
    Integer getAmountOfDrugs(@Param("drugID")Long drugID,@Param("supplierID") Long supplierID);

    WareHouse findBySupplier_id(Long supplierID);

    @Modifying
    @Query(value = "UPDATE warehouse_drug \n" +
            "SET amount = amount + :amount \n" +
            "WHERE drug_id = :drugID AND warehouse_id = :warehouseID"
            ,nativeQuery = true)
    void updateAmount(@Param("amount")Integer amount,@Param("drugID")Long drugID,@Param("warehouseID")Long warehouseID);
}
