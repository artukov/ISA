package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.SupplyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {
}
