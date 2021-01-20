package isa.project.pharmacyapp.service;
import isa.project.pharmacyapp.dto.DateLimitsDTO;
import isa.project.pharmacyapp.dto.PharmacyDTO;
import isa.project.pharmacyapp.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PharmacyService {

    public abstract PharmacyDTO findById(Long id);

    public abstract List<PharmacyDTO> findAll();

    public abstract void createNewPharmacy(PharmacyDTO pharmacyDTO) throws Exception;

    public abstract PharmacyDTO modifyPharmacy(Long id, PharmacyDTO pharmacyDTO) throws Exception;

    public abstract void deletePharmacy(Long id) throws Exception;

    Double calculateFinances(DateLimitsDTO limitsDTO, Long id);

    Address getAddress(Long id);
}
