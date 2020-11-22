package isa.project.pharmacyapp.service;

<<<<<<< HEAD
public interface PharmacyService {
=======

import isa.project.pharmacyapp.dto.PharmacyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PharmacyService {

    public abstract PharmacyDTO findById(Long id);

    public abstract List<PharmacyDTO> findAll();

    public abstract void createNewPharmacy(PharmacyDTO pharmacyDTO);

    public abstract void modifyPharmacy(Long id, PharmacyDTO pharmacyDTO);

    public abstract void deletePharmacy(Long id);
>>>>>>> srdjan
}
