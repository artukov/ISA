package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.AppointmentDTO;
import isa.project.pharmacyapp.dto.ConsultationDTO;

import isa.project.pharmacyapp.model.Calendar;
import isa.project.pharmacyapp.model.Consultation;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.model.embedded_ids.CalendarAppointmentsID;
import isa.project.pharmacyapp.model.many2many.CalendarAppointments;
import isa.project.pharmacyapp.repository.*;
import isa.project.pharmacyapp.service.CalendarService;
import isa.project.pharmacyapp.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        this.saveConsultation(consultation, dto);
    }

    @Override
    public void saveConsultation(Consultation consultation, ConsultationDTO consultationDTO) throws Exception {
        ConsultationDTO.dto2Consultation(consultation, consultationDTO);

        try {
            consultation.setPharmacist(pharmacistRepository.findById(consultationDTO.getPharmacistID()).orElse(null));
            consultation.setPatient(patientRepository.findById(consultationDTO.getPatient_id()).orElse(null));

            for(Long drugID : consultationDTO.getDrugs()){
                consultation.getDrug().add(drugRepository.findById(drugID).orElse(null));
            }
        }
        catch (Exception e){
            throw new Exception();
        }

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
