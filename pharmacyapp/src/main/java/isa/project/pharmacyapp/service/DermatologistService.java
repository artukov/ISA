package isa.project.pharmacyapp.service;


import isa.project.pharmacyapp.dto.DermatologistDTO;

import java.util.List;

public interface DermatologistService {

    public abstract DermatologistDTO findById(Long id);
    public abstract List<DermatologistDTO> findAllByPharmacy(Long pharmacyId);
    public abstract void createNewDermatologist(DermatologistDTO dermatologistDTO) throws Exception;
    public abstract void modifyDermatologist(Long id, DermatologistDTO dermatologistDTO) throws Exception;
    public abstract void deleteDermatologist(Long id) throws Exception;
}
