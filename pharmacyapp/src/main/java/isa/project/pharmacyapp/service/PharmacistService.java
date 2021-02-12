package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.*;
import isa.project.pharmacyapp.model.AbsenceRequest;
import isa.project.pharmacyapp.model.Pharmacist;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface PharmacistService extends UserService {

    public abstract PharmacistDTO findById(Long id);
    public abstract List<PharmacistDTO> findAllByPharmacy(Long pharmacyId);
    public abstract List<CalendarDTO> getPharmacistCalendar(Long pharmaId);
    public abstract List<PatientDTO> findAllPharmacistPatients(Long pharmaId, String orderCondition);
    public abstract List<PatientDTO> getAllPatients();
    public abstract List<PatientDTO> findPatientbyNameAndSurname(String firstName, String lastName);
    public abstract void createNewPharmacist(PharmacistDTO dto) throws Exception;
    public abstract void modifyPharmacist(Long id, PharmacistDTO dto) throws Exception;
    public abstract void deletePharmacistById(Long id) throws Exception;
    public abstract Pharmacist savePharmacist(Pharmacist pharmacist, PharmacistDTO pharmacistDTO) throws Exception;
    public abstract List<PatientDTO> getPharmacistPatients(Long pharmacistId);

    Double getAvgRating(Long id) throws Exception;

    List<PatientDTO> getPharmacistPatientsDistinct(Long pharmacistId);
    List<PatConsDTO> getPharmacistConsultations(Long pharmacistId);
    List<PatConsDTO> getPharmacistCalendar(Long pharmacistId, Integer month, Integer year);
}
