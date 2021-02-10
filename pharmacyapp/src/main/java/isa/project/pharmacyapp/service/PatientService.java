package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.ConsultationDTO;
import isa.project.pharmacyapp.dto.DermatologistDTO;
import isa.project.pharmacyapp.dto.PatientDTO;
import isa.project.pharmacyapp.model.Dermatologist;
import isa.project.pharmacyapp.model.Patient;

import java.util.List;

public interface PatientService extends UserService{
    public abstract List<PatientDTO> getAllPatients();
    public abstract Patient savePatient(Patient patient, PatientDTO patientDTO) throws Exception;
    public abstract void addPenalty(Long id) throws Exception;
    public abstract PatientDTO getPatient(Long id);
}
