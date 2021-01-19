package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.CalendarDTO;
import isa.project.pharmacyapp.dto.DermatologistDTO;
import isa.project.pharmacyapp.dto.PatientDTO;
import isa.project.pharmacyapp.dto.PharmacistDTO;
import isa.project.pharmacyapp.model.Pharmacist;


import java.util.List;

public interface PharmacistService extends UserService {

    public abstract PharmacistDTO findById(Long id);
    public abstract List<PharmacistDTO> findAllByPharmacy(Long pharmacyId);
    public abstract List<CalendarDTO> getPharmacistCalendar(Long pharmaId);
    public abstract List<PatientDTO> findAllPatients(Long pharmaId, String orderCondition);
    public abstract void createNewPharmacist(PharmacistDTO dto) throws Exception;
    public abstract void modifyPharmacist(Long id, PharmacistDTO dto) throws Exception;
    public abstract void deletePharmacistById(Long id) throws Exception;
    public abstract Pharmacist savePharmacist(Pharmacist pharmacist, PharmacistDTO pharmacistDTO);

    Double getAvgRating(Long id) throws Exception;
}
