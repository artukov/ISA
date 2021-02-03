package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.*;
import isa.project.pharmacyapp.exception.DeletingDermatologistException;
import isa.project.pharmacyapp.model.*;
import isa.project.pharmacyapp.model.many2many.PharmacyDermatologist;
import isa.project.pharmacyapp.repository.*;
import isa.project.pharmacyapp.service.DermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
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

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AuthenticationRepository authenticationRepository;


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

            dto.setRatings(dermatologistRepository.getAvgRatings(dermatologist.getId(),pharmacyId));

            dermatologistDTOs.add(dto);
        }

        return dermatologistDTOs;
    }

    @Override
    public List<PatientDTO> findAllDermaPatients(Long dermaId, String orderCondition) {
        List<Patient> patientList = this.patientRepository.findDermatologistPatients(dermaId,orderCondition);

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
    public List<CalendarDTO> getDermatologistCalendar(Long dermaId){
        List<Object[]> calendar = this.calendarRepository.getDermatologistCalendar(dermaId);
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
    public void createNewDermatologist(DermatologistDTO dermatologistDTO) throws Exception {
//        if(!dermatologistRepository.existsById(dermatologistDTO.getId())){
//
//        }
        Dermatologist dermatologist = new Dermatologist();
        List<Authority> authorities = new ArrayList<>();
//        Authority authority = ;
        authorities.add(authenticationRepository.findByName("USER"));
        dermatologist.setAuthorities(authorities);
        dermatologist.setLastPasswordResetDate(null);

        this.saveDermatologist(dermatologist,dermatologistDTO);

    }

    @Override
    public void modifyDermatologist(Long dermaId, DermatologistDTO dermatologistDTO) throws Exception {
        Dermatologist dermatologist = dermatologistRepository.findById(dermaId).orElse(null);

        if(dermatologist == null){
            throw new NoSuchElementException("DermatologistSerivceImpl::modifyDermatologist(Long id, DermatologistDTO dermatologistDTO)" +
                    "dermatologist could not be find by the given id");
        }

        try {
            this.saveDermatologist(dermatologist, dermatologistDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("DermatologistServiceImpl::modifyDermatologist(Long id, DermatologistDTO dermatologistDTO)" +
                    "saving of the modified object did not excecute");
        }
    }

    @Override
    public void deleteDermatologist(Long id) throws Exception {

    }

    @Override
    public Dermatologist saveDermatologist(Dermatologist dermatologist, DermatologistDTO dermatologistDTO) throws Exception{
        UserDTO.dto2User(dermatologist,dermatologistDTO);

        try{
            dermatologist.setAddress(addressRepository.findById(dermatologistDTO.getAddress_id()).orElse(null));
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("DermatologistSerivceImpl::saveDermatologist(Dermatologist dermatologist, DermatologistDTO dermatologistDTO)" +
                    " address does not exists");
        }



        try {
            dermatologistRepository.save(dermatologist);
        }
        catch(Exception e){
            e.printStackTrace();
            throw new Exception("DermatologistSerivceImpl::saveDermatologist(Dermatologist dermatologist, DermatologistDTO dermatologistDTO)" +
                    "saving dermatologist exception ");
        }

        return dermatologist;
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
            throw new DeletingDermatologistException("Dermatologist has unfinished examinations");
//            throw  new Exception("Dermatologist has unfinished examinations");
        }

        dermatologistRepository.deleteDermaFromPharmacy(dermaID, pharmacyID);

        dermatologistRepository.deleteRatingsForPharmacy(dermaID,pharmacyID);
        dermatologistRepository.deleteRatingsFromPharmacy(dermaID, pharmacyID);



    }

    @Override
    public List<DermatologistDTO> findAllNotInPharmacy(Long pharmacyID) throws Exception {
        if(!pharmacyRepository.existsById(pharmacyID)){
            throw new Exception("No pharmacy with given id");
        }

        List<Dermatologist> dermatologists = dermatologistRepository.findAllNotInPharmacy(pharmacyID);
        ArrayList<DermatologistDTO> dermatologistDTOS = new ArrayList<>();
        for(Dermatologist dermatologist : dermatologists){
            dermatologistDTOS.add(DermatologistDTO.dermatologist2Dto(dermatologist));
        }

        return dermatologistDTOS;

    }

    @Override
    public User findByEmail(String email) {
        return this.dermatologistRepository.findByEmail(email);
    }

    @Override
    public User saveNewUser(UserDTO userDTO) throws Exception {
        return this.saveDermatologist(new Dermatologist(), (DermatologistDTO) userDTO);
    }

    @Override
    public List<PharmaDermaDTO> getDermaPharmacies(Long dermaId){
//        List<Object[]> pharmaDerma = pharmacyRepository.getDermaPharmacy(dermaId);
//        PharmaDermaDTO dto = new PharmaDermaDTO();
//        Object[] obj = pharmaDerma.get(0);
//        dto.setHours((Integer) obj[0]);
//        dto.setStart_hour((Date) obj[1]);
//        Pharmacy pharmacy = pharmacyRepository.findById((Long) obj[2]).orElse(null);
//        dto.setPharmacy_id((BigInteger) pharmacy.getId());
//        Dermatologist dermatologist = dermatologistRepository.findById((Long) obj[3]).orElse(null);
//        dto.setDerma_id(dermatologist.getId());
        Dermatologist derma = dermatologistRepository.findById(dermaId).orElse(null);
    List<PharmaDermaDTO> dtos = new ArrayList<>();

     for(PharmacyDermatologist pd: derma.getPharmacies()){
         PharmaDermaDTO dto = new PharmaDermaDTO();
         dto.setStart_hour(pd.getStartHour());
         dto.setHours(pd.getHours());
         dto.setDerma_id(dermaId);
         dto.setPharmacy_id(pd.getId().getPharmacy().getId());
         dtos.add(dto);
     }

        return dtos;
    }



}
