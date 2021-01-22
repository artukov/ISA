package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.DermatologistDTO;
import isa.project.pharmacyapp.dto.PatientDTO;
import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.model.Dermatologist;
import isa.project.pharmacyapp.model.Patient;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.repository.AddressRepository;
import isa.project.pharmacyapp.repository.PatientRepository;
import isa.project.pharmacyapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<PatientDTO> getAllPatients(){
        List <Patient> patientList = this.patientRepository.findAll();
        ArrayList<PatientDTO> patientDTOs = new ArrayList<>();

        for(Patient patient : patientList){
            PatientDTO dto = PatientDTO.patient2Dto(patient);
            patientDTOs.add(dto);
        }
        return patientDTOs;
    }

    @Override
    public Patient savePatient(Patient patient, PatientDTO patientDTO) throws Exception{
        UserDTO.dto2User(patient,patientDTO);

        try{
            patient.setAddress(addressRepository.findById(patientDTO.getAddress_id()).orElse(null));
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("PatientSerivceImpl::savePatient(Patient patient, PatientDTO patientDTO)" +
                    " address does not exists");
        }

        try {
            patientRepository.save(patient);
        }
        catch(Exception e){
            e.printStackTrace();
            throw new Exception("PatientSerivceImpl::savePatient(Patient patient, PatientDTO patientDTO)" +
                    "saving patient exception ");
        }

        return patient;
    }

    @Override
    public User findByEmail(String email) {
        return this.patientRepository.findByEmail(email);
    }

    @Override
    public User saveNewUser(UserDTO userDTO) throws Exception {
        return this.savePatient(new Patient(), (PatientDTO) userDTO);
    }
}