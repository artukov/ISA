package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionsRepository extends JpaRepository<Promotions, Long> {
}
