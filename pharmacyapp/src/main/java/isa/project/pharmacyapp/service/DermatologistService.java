package isa.project.pharmacyapp.service;


import isa.project.pharmacyapp.dto.DermatologistDTO;
import isa.project.pharmacyapp.model.Dermatologist;

import java.util.List;

public interface DermatologistService extends UserService {

    public abstract DermatologistDTO findById(Long id);
    public abstract List<DermatologistDTO> findAllByPharmacy(Long pharmacyId);
    public abstract void createNewDermatologist(DermatologistDTO dermatologistDTO) throws Exception;
    public abstract void modifyDermatologist(Long id, DermatologistDTO dermatologistDTO) throws Exception;
    public abstract void deleteDermatologist(Long id) throws Exception;
    public abstract Dermatologist saveDermatologist(Dermatologist dermatologist, DermatologistDTO dermatologistDTO);

    Double getAvgRatings(Long dermaID, Long pharmacyID) throws Exception;

    void deleteDermatologistPharmacy(Long dermaID, Long pharmacyID) throws Exception;
}
