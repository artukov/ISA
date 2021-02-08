package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.ConsultationDTO;
import isa.project.pharmacyapp.dto.DermatologistDTO;
import isa.project.pharmacyapp.dto.ExaminationDTO;
import isa.project.pharmacyapp.dto.WorkingHoursDTO;
import isa.project.pharmacyapp.model.Consultation;

import java.util.Date;

public interface ConsultationService {


    void createNewConsultation(ConsultationDTO dto) throws Exception;
    void modifyConsultation(ConsultationDTO consultationDTO) throws Exception;
    void saveConsultation(Consultation consultation, ConsultationDTO consultationDTO) throws Exception;
    ConsultationDTO findConsultation(Long patientId, Date dateTime);
}
