package isa.project.pharmacyapp.service;


import isa.project.pharmacyapp.dto.*;
import isa.project.pharmacyapp.model.Dermatologist;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DermatologistService extends UserService {

    public abstract DermatologistDTO findById(Long id);
    public abstract List<DermatologistDTO> findAllByPharmacy(Long pharmacyId);
    public abstract List<CalendarDTO> getDermatologistCalendar(Long dermaId);
    public abstract List<PatientDTO> findAllDermaPatients(Long dermaId, String orderCondition);
    public abstract List<PatientDTO> getDermaPatients(Long dermaId);
    public abstract List<PatientDTO> getAllPatients();
    public abstract List<PatientDTO> findPatientbyNameAndSurname(String firstName, String lastName);
    public abstract void createNewDermatologist(DermatologistDTO dermatologistDTO) throws Exception;
    public abstract void modifyDermatologist(Long dermaId, DermatologistDTO dermatologistDTO) throws Exception;
    public abstract void deleteDermatologist(Long id) throws Exception;
    public abstract Dermatologist saveDermatologist(Dermatologist dermatologist, DermatologistDTO dermatologistDTO) throws Exception;
    public abstract List<PharmaDermaDTO> getDermaPharmacies(Long dermaId);
    Double getAvgRatings(Long dermaID, Long pharmacyID) throws Exception;

    void deleteDermatologistPharmacy(Long dermaID, Long pharmacyID) throws Exception;

    List<DermatologistDTO> findAllNotInPharmacy(Long pharmacyID) throws Exception;

    List<PatientDTO> getDermaPatientsDistinct(Long dermaId);
    List<PharmacyDTO> getDermatologistPharmacies(Long dermaId);
    List<PatConsDTO> getDermatologistsExaminations(Long dermaId);
    List<PatConsDTO> getDermatologistCalendar(Long dermaId,Integer month,Integer year);
}
