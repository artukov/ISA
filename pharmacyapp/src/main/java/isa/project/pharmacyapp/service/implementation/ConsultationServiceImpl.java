package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.AppointmentDTO;
import isa.project.pharmacyapp.dto.ConsultationDTO;

import isa.project.pharmacyapp.model.Calendar;
import isa.project.pharmacyapp.model.Consultation;
import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.Patient;
import isa.project.pharmacyapp.model.Pharmacist;
import isa.project.pharmacyapp.repository.ConsultationRepository;
import isa.project.pharmacyapp.repository.DrugRepository;
import isa.project.pharmacyapp.repository.PatientRepository;
import isa.project.pharmacyapp.repository.PharmacistRepository;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.model.embedded_ids.CalendarAppointmentsID;
import isa.project.pharmacyapp.model.many2many.CalendarAppointments;
import isa.project.pharmacyapp.repository.*;
import isa.project.pharmacyapp.service.CalendarService;
import isa.project.pharmacyapp.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void createNewConsultation(ConsultationDTO dto) throws Exception {
        Consultation consultation = new Consultation();
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
        //moram slati "drugs": [] da bi radilo nmg  da uospte ne posaljem
            List<Drug> drugs = new ArrayList<>();
            for(Long drugID: consultationDTO.getDrugs()){
                Drug drug = drugRepository.findById(drugID).orElse(null);
                if(drug == null){
                    throw new Exception("Drug does not exist");
                }
                drugs.add(drug);
            }
            consultation.setDrug(drugs);


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
}
