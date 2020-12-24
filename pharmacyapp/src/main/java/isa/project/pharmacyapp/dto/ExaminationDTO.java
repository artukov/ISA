package isa.project.pharmacyapp.dto;


import isa.project.pharmacyapp.model.Examination;

import java.util.Date;
import java.util.List;

public class ExaminationDTO extends AppointmentDTO {

    private Double price;
    private String diagnose;
    private Long dermatologist_id;

    public ExaminationDTO() {
    }

    public ExaminationDTO(Long id, String report, Date beggingDateTime,
                          int duration, List<Long> drugs, Long patient_id, Double price, String diagnose, Long dermatologist_id) {
        super(id, report, beggingDateTime, duration, drugs, patient_id);
        this.price = price;
        this.diagnose = diagnose;
        this.dermatologist_id = dermatologist_id;
    }

    public ExaminationDTO(Long id, String report, Date beggingDateTime, int duration, List<Long> drugs, Long patient_id) {
        super(id, report, beggingDateTime, duration, drugs, patient_id);
    }

    public static void dto2Examination(Examination examination, ExaminationDTO examinationDTO) {
        examination.setDiagnose(examinationDTO.getDiagnose());
        examination.setPrice(examinationDTO.getPrice());
        examination.setBeggingDateTime(examinationDTO.getBeggingDateTime());
        examination.setDuration(examinationDTO.getDuration());
        examination.setReport(examinationDTO.getReport());

    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public Long getDermatologist_id() {
        return dermatologist_id;
    }

    public void setDermatologist_id(Long dermatologist_id) {
        this.dermatologist_id = dermatologist_id;
    }
}
