package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.CalendarDTO;
import isa.project.pharmacyapp.dto.PatientDTO;
import isa.project.pharmacyapp.dto.PharmacistDTO;
import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.model.*;
import isa.project.pharmacyapp.repository.*;
import isa.project.pharmacyapp.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

//    @Autowired
//    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationRepository authenticationRepository;

    final private static String EXCEPTION_TEXT = "PharmacistServiceImpl::";
    final private static String DOES_NOT_EXISTS = " pharmacist with given id does not exists";
    final private static String UNSUCCESSFUL_EXECUTION = " did not execute successfully";

    @Override
    public PharmacistDTO findById(Long id) {
        Pharmacist pharmacist = pharmacistRepository.findById(id).orElse(null);

        if(pharmacist == null)
            throw new NoSuchElementException(EXCEPTION_TEXT + "findById" + DOES_NOT_EXISTS);

        PharmacistDTO dto = PharmacistDTO.pharmacist2Dto(pharmacist);

        return dto;
    }

    @Override
    public List<PharmacistDTO> findAllByPharmacy(Long pharmacyId) {
        List<Pharmacist> pharmacistList = pharmacistRepository.findAllByPharmacy(pharmacyId);

        ArrayList<PharmacistDTO> pharmacistDTOS = new ArrayList<>();
        for(Pharmacist pharmacist : pharmacistList){
            pharmacistDTOS.add(PharmacistDTO.pharmacist2Dto(pharmacist));
        }


        return pharmacistDTOS;
    }

    @Override
    public List<PatientDTO> findAllPharmacistPatients(Long pharmaId, String orderCondition) {
        List<Patient> patientList = this.patientRepository.findPharmacistPatients(pharmaId,orderCondition);

        ArrayList<PatientDTO> patientDTOs = new ArrayList<>();

        for(Patient patient : patientList){
            PatientDTO dto = PatientDTO.patient2Dto(patient);
            patientDTOs.add(dto);
        }
        return patientDTOs;
    }

    @Override
    public List<PatientDTO> getAllPatients(){
        List<Patient> patientList = this.patientRepository.findAll();
        ArrayList<PatientDTO> patientDTOs = new ArrayList<>();

        for(Patient patient : patientList){
            PatientDTO dto = PatientDTO.patient2Dto(patient);
            patientDTOs.add(dto);
        }
        return patientDTOs;
    }
    @Override
    public List<PatientDTO> findPatientbyNameAndSurname(String firstName, String lastName){
        List<Patient> patientList = this.patientRepository.findPatientByNameAndSurname(firstName,lastName);
        ArrayList<PatientDTO> patientDTOs = new ArrayList<>();

        for(Patient patient : patientList){
            PatientDTO dto = PatientDTO.patient2Dto(patient);
            patientDTOs.add(dto);
        }
        return patientDTOs;
    }

    @Override
    public List<CalendarDTO> getPharmacistCalendar(Long pharmaId){
        List<Object[]> calendar = this.calendarRepository.getPharmacistCalendar(pharmaId);
        ArrayList<CalendarDTO> calendarDTOs = new ArrayList<>();
        for(Object[] obj : calendar){
            CalendarDTO dto = new CalendarDTO();
            dto.setCalendar_id((BigInteger) obj[0]);
            dto.setAppointment_id((BigInteger) obj[1]);
            dto.setDate((Date) obj[2]);
            dto.setPharmacy_id((BigInteger) obj[3]);
            calendarDTOs.add(dto);
        }
        return calendarDTOs;
    }

    @Override
    public void createNewPharmacist(PharmacistDTO dto) throws Exception {
        if(!pharmacyRepository.existsById(dto.getPharmacyID()))
            throw new Exception("Pharmacy with given id does not exists");

        Pharmacist pharmacist = new Pharmacist();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authenticationRepository.findByName("USER"));
        pharmacist.setAuthorities(authorities);
        pharmacist.setRatings(new ArrayList<>());
        pharmacist.setPassword(dto.getPassword());
        pharmacist.setLastPasswordResetDate(null);

        this.savePharmacist(pharmacist, dto);
    }

    @Override
    public void modifyPharmacist(Long id, PharmacistDTO dto) throws Exception {
        Pharmacist pharmacist = pharmacistRepository.findById(id).orElse(null);

        if(pharmacist == null){
            throw new NoSuchElementException("PharmacistSerivceImpl::modifyPharmacist(Long id, PharmacistDTO dto)" +
                    "pharmacist could not be find by the given id");
        }

        try {
            this.savePharmacist(pharmacist, dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("PharmacistServiceImpl::modifyPharmacist(Long id, PharmacistDTO pharmacistDTO)" +
                    "saving of the modified object did not excecute");
        }
    }

    @Transactional
    @Override
    public void deletePharmacistById(Long id) throws Exception {
//        Pharmacist pharmacist = pharmacistRepository.findById(id).orElse(null);
        if(!pharmacistRepository.existsById(id)){
            throw new Exception("There is no pharmacist with given id");
        }

        if(pharmacistRepository.existsUnfinishedConsultation(id) != 0.0){
            throw new Exception("Pharmacist has a consultation");
        }

        try{
            pharmacistRepository.deletePharmacistFromPharmacy(id);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("Deleting pharmacist from the pharmacy");
        }

    }

    @Override
    public Pharmacist savePharmacist(Pharmacist pharmacist, PharmacistDTO pharmacistDTO) throws Exception{

        UserDTO.dto2User(pharmacist, pharmacistDTO);
        pharmacist.setRole(UserRoles.PHARMACIST);
        pharmacist.setHours(pharmacistDTO.getHours());
        pharmacist.setStart_hour(pharmacistDTO.getStart_hour());


        pharmacist.setAddress(addressRepository.save(pharmacistDTO.getAddress()));



        try{
//            pharmacist.setAddress(addressRepository.findById(pharmacistDTO.getAddress_id()).orElse(null));
            pharmacist.setPharmacy(pharmacyRepository.getOne(pharmacistDTO.getPharmacyID()));
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("PharmacistSerivceImpl::savePharmacist(Pharmacist pharmacist, PharmacistDTO pharmacistDTO)" +
                    " address does not exists");
        }


        try {
            pharmacistRepository.save(pharmacist);
        }
        catch(Exception e){
            e.printStackTrace();
            throw new Exception("PharmacistSerivceImpl::savePharmacist(Pharmacist pharmacist, PharmacistDTO pharmacistDTO)" +
                    "saving pharmacist exception ");
        }
        return pharmacist;
    }

    @Override
    public Double getAvgRating(Long id) throws Exception {

        if(pharmacistRepository.findById(id).orElse(null) == null){
            throw new Exception(getClass().getName()+"::getAvgRating pharmacist does not exists");
        }


        return pharmacistRepository.getAvgRatings(id);
    }

    /**************************************
     * UserService methods
     *
     * *************************************/

    @Override
    public User findByEmail(String email) {
        return this.pharmacistRepository.findByEmail(email);
    }

    @Override
    public User saveNewUser(UserDTO userDTO) {
        try {
           return this.savePharmacist(new Pharmacist(), (PharmacistDTO) userDTO);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PatientDTO> getPharmacistPatients(Long pharmacistId){
        List<Patient> patientList = this.patientRepository.getPharmacistPatients(pharmacistId);

        ArrayList<PatientDTO> patientDTOs = new ArrayList<>();

        for(Patient patient : patientList){
            PatientDTO dto = PatientDTO.patient2Dto(patient);

//            List<Examination> exams = examinationRepository.findByDermaIdAndPatientId(dermaId,patient.getId());
//            for(Examination exam : exams){
//                dto.getAppointment().add(exam.getId());
//            }

            patientDTOs.add(dto);
        }
        return patientDTOs;

    }

    @Override
    public List<PatientDTO> getPharmacistPatientsDistinct(Long pharmacistId){
        List<Patient> patientList = this.patientRepository.getPharmacistPatientsDistinct(pharmacistId);

        ArrayList<PatientDTO> patientDTOs = new ArrayList<>();

        for(Patient patient : patientList){
            PatientDTO dto = PatientDTO.patient2Dto(patient);

//            List<Examination> exams = examinationRepository.findByDermaIdAndPatientId(dermaId,patient.getId());
//            for(Examination exam : exams){
//                dto.getAppointment().add(exam.getId());
//            }

            patientDTOs.add(dto);
        }
        return patientDTOs;

    }
}
