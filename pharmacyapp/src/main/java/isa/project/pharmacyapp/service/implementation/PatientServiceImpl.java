package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.*;
import isa.project.pharmacyapp.model.*;
import isa.project.pharmacyapp.repository.AddressRepository;
import isa.project.pharmacyapp.repository.AllergyRepository;
import isa.project.pharmacyapp.repository.DrugRepository;
import isa.project.pharmacyapp.repository.PatientRepository;
import isa.project.pharmacyapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private AllergyRepository allergyRepository;

    @Autowired
    private AuthorityServiceImpl authorityService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

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
    public void modifyPatient(Long id, PatientDTO dto) throws Exception {
        Patient patient = patientRepository.findById(id).orElse(null);

        if (patient == null) {
            throw new NoSuchElementException("PatientSerivceImpl::modifyPatient(Long id, PatientDTO dto)" +
                    "patient could not be find by the given id");
        }

        try {
            this.savePatient(patient, dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("PatientServiceImpl::modifyPatient(Long id, PatientDTO patientDTO)" +
                    "saving of the modified object did not excecute");
        }
    }

    @Override
    public Patient savePatient(Patient patient, PatientDTO patientDTO) throws Exception{
        UserDTO.dto2User(patient,patientDTO);

        patient.setAddress(addressRepository.save(patientDTO.getAddress()));

//        try{
//            patient.setAddress(addressRepository.findById(patientDTO.getAddress_id()).orElse(null));
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new Exception("PatientSerivceImpl::savePatient(Patient patient, PatientDTO patientDTO)" +
//                    " address does not exists");
//        }

        if(patient.getAuthorities() == null){
            List<Authority> authorities = this.authorityService.findByName("USER");
            patient.setAuthorities(authorities);
            patient.setLastPasswordResetDate(null);
        }

//        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
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
    public Patient saveNewUser(UserDTO userDTO) throws Exception {
        Patient patient = new Patient();
        PatientDTO patientDTO = new PatientDTO(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getFirstname(),
                userDTO.getLastname(),
                userDTO.getAddress_id(),
                userDTO.getPhoneNumber(),
                UserRoles.PATIENT,
                null
        );
        patientDTO.setAddress(new Address());
        patient.setPassword(patientDTO.getPassword());
        patient.setEnabled(true);

        return this.savePatient(patient, patientDTO);
    }

    @Override
    public void addPenalty(Long id) throws Exception {

        Patient patient = patientRepository.findById(id).orElse(null);
        Integer penalties = 0;
        if(patient.getPenalties() == null) {
            penalties = 1;
        }else{
            penalties = patient.getPenalties()+1;
        }
        patient.setPenalties(penalties);
        if(patient == null){
            throw new NoSuchElementException("PatientSerivceImpl::modifyPatient(PatientDTO, patientDTO)" +
                    "patient could not be find by the given id");
        }

        Authority authority = new Authority();

        try {
            patientRepository.save(patient);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("PatientServiceImpl::modifyPatient(PatientDTO patientDTO)" +
                    "saving of the modified object did not excecute");
        }
    }

    @Override
    public PatientDTO getPatient(Long id){
        Patient p = patientRepository.findById(id).orElse(null);
        PatientDTO dto = new PatientDTO();
        return dto.patient2Dto(p);

    }

    @Override
    public void addAllergy(Long patientId, Long drugId) throws Exception{
        Allergy allergy = new Allergy();
        Drug drug = drugRepository.findById(drugId).orElse(null);
        if(allergy.getDrug()==null){
            allergy.setDrug(new ArrayList<>());
        }
        allergy.getDrug().add(drug);
        allergy.setPatient(patientRepository.findById(patientId).orElse(null));
        try {
            //consultation =consultationRepository.save(consultation);
            allergyRepository.save(allergy);
        }
        catch (Exception e){
            throw new Exception();
        }
    }

}
