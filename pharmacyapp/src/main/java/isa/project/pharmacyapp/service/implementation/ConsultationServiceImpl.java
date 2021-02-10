package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.*;

import isa.project.pharmacyapp.exception.AllergyException;
import isa.project.pharmacyapp.exception.InsertingConsultationException;
import isa.project.pharmacyapp.exception.InsertingDermatologistException;
import isa.project.pharmacyapp.model.*;
import isa.project.pharmacyapp.repository.ConsultationRepository;
import isa.project.pharmacyapp.repository.DrugRepository;
import isa.project.pharmacyapp.repository.PatientRepository;
import isa.project.pharmacyapp.repository.PharmacistRepository;
import isa.project.pharmacyapp.model.embedded_ids.CalendarAppointmentsID;
import isa.project.pharmacyapp.model.many2many.CalendarAppointments;
import isa.project.pharmacyapp.repository.*;
import isa.project.pharmacyapp.service.CalendarService;
import isa.project.pharmacyapp.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private PharmacistRepository pharmacistRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private CalendarService calendarService;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private DrugNotInPharmacyStashRequestRepository drugNotInPharmacyStashRequestRepository;

    @Override
    public void createNewConsultation(ConsultationDTO dto) throws Exception {
        Consultation consultation = new Consultation();
        Date endHours = new Date();
        endHours.setTime(dto.getBeggingDateTime().getTime());
        endHours.setHours(endHours.getHours() + dto.getDuration());
        Double result = pharmacistRepository.overlappingWorkingHours(dto.getBeggingDateTime(), endHours,dto.getPharmacistID());
        if(result != 0.0){
            throw new InsertingConsultationException("Pharmacists hours are overlapping");
        }
        Double resultPatientConsultation = patientRepository.overlappingConsultationHours(dto.getBeggingDateTime(),endHours,dto.getPatient_id());
        if(resultPatientConsultation != 0){
            throw new InsertingConsultationException("Patient consultation hours are overlapping");
        }
        Double resultPatientExamination = patientRepository.overlappingExaminationHours(dto.getBeggingDateTime(),endHours,dto.getPatient_id());
        if(resultPatientExamination != 0){
            throw new InsertingConsultationException("Patient examination hours are overlapping");
        }
        try {
            this.saveConsultation(consultation, dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void saveConsultation(Consultation consultation, ConsultationDTO consultationDTO) throws Exception {
        consultation.setId(consultationDTO.getId());
        consultation.setBeggingDateTime(consultationDTO.getBeggingDateTime());
        consultation.setDuration(consultationDTO.getDuration());
        consultation.setReport(consultationDTO.getReport());
        consultation.setFinished(consultationDTO.getFinished());

        Pharmacist pharmacist = pharmacistRepository.findById(consultationDTO.getPharmacistID()).orElse(null);
        if(pharmacist == null){
            throw  new Exception("Pharmacist does not exists");
        }
        Patient patient = patientRepository.findById(consultationDTO.getPatient_id()).orElse(null);
        if(patient == null){
            throw  new Exception("Patient does not exists");
        }
        consultation.setPharmacist(pharmacist);
        consultation.setPatient(patient);
        //moram slati "drugs": [] da bi radilo nmg  da uospte ne posaljem created by Artukov
        Double allergiesNum = 0.0;
            List<Drug> drugs = new ArrayList<>();
            for(Long drugID: consultationDTO.getDrugs()){
                Drug drug = drugRepository.findById(drugID).orElse(null);
                allergiesNum += drugRepository.getAllergy(consultationDTO.getPatient_id(),drugID);
                if(drug == null){

                    throw new Exception("Drug does not exist");
                }
                drugs.add(drug);
            }
            if(allergiesNum > 0){
                throw new AllergyException("Patient is allergic to drug");

            }
            consultation.setDrug(drugs);

//        Date endHours = new Date();
//        endHours.setTime(consultationDTO.getBeggingDateTime().getTime());
//        endHours.setHours(endHours.getHours() + consultationDTO.getDuration());
//        Double result = pharmacistRepository.overlappingWorkingHours(consultationDTO.getBeggingDateTime(), endHours,consultationDTO.getPharmacistID());
//        if(result != 0.0){
//            throw new InsertingConsultationException("Pharmacists hours are overlapping");
//        }
//        Double resultPatientConsultation = patientRepository.overlappingConsultationHours(consultationDTO.getBeggingDateTime(),endHours,consultationDTO.getPatient_id());
//        if(resultPatientConsultation != 0){
//            throw new InsertingConsultationException("Patient consultation hours are overlapping");
//        }
//        Double resultPatientExamination = patientRepository.overlappingExaminationHours(consultationDTO.getBeggingDateTime(),endHours,consultationDTO.getPatient_id());
//        if(resultPatientExamination != 0){
//            throw new InsertingConsultationException("Patient examination hours are overlapping");
//        }
        try {
            consultation =consultationRepository.save(consultation);
            /**
             * If the appointment exists already in the calendar then there is no need
             * To insert another raw into calendar_appointments table,
             * this is when the dermatologist/pharmacist modifies the appointment with status finished or something similar
             * On the other hand, if appointment does not exists, program is obliged to insert new row*/
            if(!calendarService.checkIfAppointmentExists(consultationDTO.getPharmacyID(), consultation.getId())){
                calendarService.saveAppointment(consultationDTO.getPharmacyID(), consultation);
            }
        }
        catch (Exception e){
            throw new Exception();
        }
    }

    @Override
    public void modifyConsultation(ConsultationDTO consultationDTO) throws Exception {
        Consultation consultation = consultationRepository.findById(consultationDTO.getId()).orElse(null);
        //consultationRepository.deleteById(consultationDTO.getId());
        if(consultation == null){
            throw new NoSuchElementException("ConsultationSerivceImpl::modifyConsultation(Long id, ConsultationDTO consultationDTO)" +
                    "consultation could not be find by the given id");
        }
        for(Long drugID : consultationDTO.getDrugs()){
            Integer drugAmount = drugRepository.getAmount(drugID,consultationDTO.getPharmacyID());
            if(drugAmount == 0 || drugAmount == null ){
                DrugNotInPharmacyStashRequest request = new DrugNotInPharmacyStashRequest();
                request.setAppointment(consultation);
                request.setUser(consultation.getPharmacist());
                request.setPharmacy(pharmacyRepository.getOne(consultationDTO.getPharmacyID()));
                request.setDrug(drugRepository.getOne(drugID));
                drugNotInPharmacyStashRequestRepository.save(request);
                throw new Exception("Pharmacy has not enough drug in stash : "+ drugID);

            }
        }


        try {
            this.saveConsultation(consultation, consultationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ConsultationDTO findConsultation(Long patientId, Date dateTime){
        Consultation c = consultationRepository.findConsultation(patientId,dateTime);
        Long pharmacyId = consultationRepository.getPharmacyIdByConsultation(c.getId());
        ConsultationDTO dto = ConsultationDTO.consultation2DTO(c);
        dto.setPharmacyID(pharmacyId);
        return dto;
    }

    @Override
    public List<ConsultationDTO> getPatientsConsultations(Long patientId){
        List<Consultation> consultations = consultationRepository.getPatientsConsultations(patientId);
        List<ConsultationDTO> dtos = new ArrayList<>();
        for(Consultation c: consultations) {
            ConsultationDTO dto = new ConsultationDTO();
            dto.setBeggingDateTime(c.getBeggingDateTime());
            dto.setDuration(c.getDuration());
            dto.setPrice(c.getPrice());
            dto.setId(c.getId());
            dtos.add(dto);
        }
        return  dtos;
    }
}
