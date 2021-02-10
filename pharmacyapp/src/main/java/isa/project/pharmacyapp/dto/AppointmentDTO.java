package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class AppointmentDTO {

    protected Long id;
    protected String report;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    protected Date beggingDateTime;
    protected int duration;
    protected Boolean finished;
    protected List<Long> drugs;
    protected Long patient_id;
    protected Double price;

    protected Long pharmacyID;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Long id, String report, Date beggingDateTime, int duration, Boolean finished, List<Long> drugs, Long patient_id) {
        this.id = id;
        this.report = report;
        this.beggingDateTime = beggingDateTime;
        this.duration = duration;
        this.finished = finished;
        this.drugs = drugs;
        this.patient_id = patient_id;
    }


    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Date getBeggingDateTime() {
        return beggingDateTime;
    }

    public void setBeggingDateTime(Date beggingDateTime) {
        this.beggingDateTime = beggingDateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Long> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Long> drugs) {
        this.drugs = drugs;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public Long getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(Long pharmacyID) {
        this.pharmacyID = pharmacyID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
