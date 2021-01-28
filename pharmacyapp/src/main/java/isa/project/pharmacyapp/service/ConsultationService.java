package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.ConsultationDTO;
import isa.project.pharmacyapp.dto.WorkingHoursDTO;
import isa.project.pharmacyapp.model.Consultation;

public interface ConsultationService {


    void createNewConsultation(ConsultationDTO dto) throws Exception;

    void saveConsultation(Consultation consultation, ConsultationDTO consultationDTO) throws Exception;
}
