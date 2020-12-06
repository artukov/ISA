package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.PharmacistDTO;


import java.util.List;

public interface PharmacistService {

    public abstract PharmacistDTO findById(Long id);
    public abstract List<PharmacistDTO> findAllByPharmacy(Long pharmacyId);
    public abstract void createNewPharmacist(PharmacistDTO dto) throws Exception;
    public abstract void modifyPharmacist(Long id, PharmacistDTO dto) throws Exception;
    public abstract void deletePharmacistById(Long id) throws Exception;
}
