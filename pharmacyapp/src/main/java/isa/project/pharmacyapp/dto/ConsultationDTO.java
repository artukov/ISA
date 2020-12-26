package isa.project.pharmacyapp.dto;

import isa.project.pharmacyapp.model.Consultation;

import java.util.Date;
import java.util.List;

public class ConsultationDTO extends AppointmentDTO {

    private Long pharmacistID;

    public ConsultationDTO() {
    }

    public ConsultationDTO(Long id, String report, Date beggingDateTime, int duration,
                           List<Long> drugs, Long patient_id, Long pharmacistID) {
        super(id, report, beggingDateTime, duration, drugs, patient_id);
        this.pharmacistID = pharmacistID;
    }

    public static void dto2Consultation(Consultation consultation, ConsultationDTO consultationDTO) {
        consultation.setBeggingDateTime(consultationDTO.getBeggingDateTime());
        consultation.setDuration(consultationDTO.getDuration());
        consultation.setReport(consultationDTO.getReport());
    }


    public Long getPharmacistID() {
        return pharmacistID;
    }

    public void setPharmacistID(Long pharmacistID) {
        this.pharmacistID = pharmacistID;
    }
}
