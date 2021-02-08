package isa.project.pharmacyapp.dto;

import isa.project.pharmacyapp.model.Consultation;
import isa.project.pharmacyapp.model.Examination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultationDTO extends AppointmentDTO {

    private Long pharmacistID;

    public ConsultationDTO() {
    }

    public ConsultationDTO(Long id, String report, Date beggingDateTime, int duration, Boolean finished, List<Long> drugs, Long patient_id) {
        super(id, report, beggingDateTime, duration, finished, drugs, patient_id);
    }

    public ConsultationDTO(Long pharmacistID) {
        this.pharmacistID = pharmacistID;
    }

    public ConsultationDTO(Long id, String report, Date beggingDateTime, int duration, Boolean finished, List<Long> drugs, Long patient_id, Long pharmacistID) {
        super(id, report, beggingDateTime, duration, finished, drugs, patient_id);
        this.pharmacistID = pharmacistID;
    }

    public static void dto2Consultation(Consultation consultation, ConsultationDTO consultationDTO) {
        consultation.setBeggingDateTime(consultationDTO.getBeggingDateTime());
        consultation.setDuration(consultationDTO.getDuration());
        consultation.setReport(consultationDTO.getReport());
        consultation.setFinished(consultationDTO.getFinished());
    }

    public static ConsultationDTO consultation2DTO(Consultation c) {
        ConsultationDTO dto = new ConsultationDTO(
                c.getId(),
                c.getReport(),
                c.getBeggingDateTime(),
                c.getDuration(),
                c.getFinished(),
                new ArrayList<>(),
                null,
                c.getPharmacist().getId()
        );
        try{
            if(c.getPatient().getId() != null)
                dto.setPatient_id(c.getPatient().getId());
        }
        catch (NullPointerException npe){

        }
        return dto;
    }


    public Long getPharmacistID() {
        return pharmacistID;
    }

    public void setPharmacistID(Long pharmacistID) {
        this.pharmacistID = pharmacistID;
    }
}
