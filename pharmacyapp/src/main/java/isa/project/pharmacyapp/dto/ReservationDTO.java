package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import isa.project.pharmacyapp.model.Reservation;

import java.util.Date;
import java.util.List;

public class ReservationDTO {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date acceptanceDate;
    private Boolean accepted;

    private List<Long> drugs;

    private Long patientID;
    private Long pharmacyID;


    public ReservationDTO() {
    }

    public ReservationDTO(Long id, Date acceptanceDate, Boolean accepted, List<Long> drugs, Long patientID, Long pharmacyID) {
        this.id = id;
        this.acceptanceDate = acceptanceDate;
        this.accepted = accepted;
        this.drugs = drugs;
        this.patientID = patientID;
        this.pharmacyID = pharmacyID;
    }

    public static void dto2reservation(Reservation reservation, ReservationDTO reservationDTO) {
        reservation.setAcceptanceDate(reservationDTO.getAcceptanceDate());
        reservation.setAccepted(reservationDTO.getAccepted());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public List<Long> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Long> drugs) {
        this.drugs = drugs;
    }

    public Long getPatientID() {
        return patientID;
    }

    public void setPatientID(Long patientID) {
        this.patientID = patientID;
    }

    public Long getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(Long pharmacyID) {
        this.pharmacyID = pharmacyID;
    }
}
