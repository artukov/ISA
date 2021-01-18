package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.DermatologistDTO;
import isa.project.pharmacyapp.dto.PatientDTO;
import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.dto.WorkingHoursDTO;
import isa.project.pharmacyapp.model.*;
import isa.project.pharmacyapp.repository.DermatologistRepository;
import isa.project.pharmacyapp.repository.PatientRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.service.DermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DermatologistServiceImpl implements DermatologistService {

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private PatientRepository patientRepository;


    final private static String EXCEPTION_TEXT = "DermatologistServiceImpl::";
    final private static String DOES_NOT_EXISTS = " dermatologist with given id does not exists";
    final private static String UNSUCCESSFUL_EXECUTION = " did not execute successfully";

    @Override
    public DermatologistDTO findById(Long id) {
        Dermatologist dermatologist = this.dermatologistRepository.findById(id).orElse(null);

        if(dermatologist == null)
            throw new NoSuchElementException(EXCEPTION_TEXT + "findById" + DOES_NOT_EXISTS);

        return DermatologistDTO.dermatologist2Dto(dermatologist);

    }

    @Override
    public List<DermatologistDTO> findAllByPharmacy(Long pharmacyId) {

        List<Dermatologist> dermatologistList = this.dermatologistRepository.findAllByPharmacy(pharmacyId);

        ArrayList<DermatologistDTO> dermatologistDTOs = new ArrayList<>();
        for(Dermatologist dermatologist : dermatologistList){
            DermatologistDTO dto = DermatologistDTO.dermatologist2Dto(dermatologist);

            List<Object[]> workingHours = this.dermatologistRepository.getWorkingHours(dermatologist.getId(),pharmacyId);

            Object[] arrayO = workingHours.get(0);
            dto.setStart_hour((Date) arrayO[0]);
            dto.setHours((Integer) arrayO[1]);
            dto.setRole(UserRoles.DERMATOLOGIST);

            dermatologistDTOs.add(dto);
        }

        return dermatologistDTOs;
    }
    @Override
    public List<PatientDTO> findAllPatients(Long dermaId, String orderCondition) {
        List<Patient> patientList = this.patientRepository.findDermatologistPatients(dermaId,orderCondition);

        ArrayList<PatientDTO> patientDTOs = new ArrayList<>();

        for(Patient patient : patientList){
            PatientDTO dto = PatientDTO.patient2Dto(patient);
            patientDTOs.add(dto);
        }
        return patientDTOs;
    }

    @Override
    public void createNewDermatologist(DermatologistDTO dermatologistDTO) throws Exception {

    }

    @Override
    public void modifyDermatologist(Long id, DermatologistDTO dermatologistDTO) throws Exception {

    }

    @Override
    public void deleteDermatologist(Long id) throws Exception {

    }

    @Override
    public Dermatologist saveDermatologist(Dermatologist dermatologist, DermatologistDTO dermatologistDTO) {
        return null;
    }

    @Override
    public Double getAvgRatings(Long dermaID, Long pharmacyID) throws Exception {

        if(( dermatologistRepository.findById(dermaID) == null) ||
                (pharmacyRepository.findById(pharmacyID).orElse(null) == null ))
            throw new Exception(getClass().getName()+"::getAvgRatings not exists");

        return dermatologistRepository.getAvgRatings(dermaID, pharmacyID);

    }

    @Transactional
    @Override
    public void deleteDermatologistPharmacy(Long dermaID, Long pharmacyID) throws Exception {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyID).orElse(null);
        if(pharmacy == null){
            throw  new Exception("Pharmacy does not exists");
        }
        Dermatologist dermatologist = dermatologistRepository.findById(dermaID).orElse(null);
        if(dermatologist == null){
            throw new Exception("Dermatologist does not exists");
        }

        if(dermatologistRepository.existsUnfinishedExamination(dermaID, pharmacyID) != 0.0){
            throw  new Exception("Dermatologist is taken");
        }

        dermatologistRepository.deleteDermaFromPharmacy(dermaID, pharmacyID);



    }

    @Override
    public User findByEmail(String email) {
        return this.dermatologistRepository.findByEmail(email);
    }

    @Override
    public User saveNewUser(UserDTO userDTO) throws Exception {
        return this.saveDermatologist(new Dermatologist(), (DermatologistDTO) userDTO);
    }


}
