package isa.project.pharmacyapp.repository;

import isa.project.pharmacyapp.model.PharmacyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public interface PharmacyAdminRepository extends JpaRepository<PharmacyAdmin, Long> {

    @Query(value="SELECT pa.* FROM pharmacy_admin pa WHERE pa.pharmacy_id = :pharmacy_id",
            nativeQuery = true)
    public List<PharmacyAdmin> findAllByPharmacyId(@Param("pharmacy_id")Long pharmacyId);

    @Modifying
    @Query(value = "DELETE FROM public.pharmacy_admin WHERE id = :adminId", nativeQuery = true)
    public void deleteById(@Param("adminId") Long id);

    PharmacyAdmin findByEmail(String email);
}
