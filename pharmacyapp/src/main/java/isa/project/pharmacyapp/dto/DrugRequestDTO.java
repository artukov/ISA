package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import isa.project.pharmacyapp.model.DrugNotInPharmacyStashRequest;
import isa.project.pharmacyapp.model.UserRoles;

public class DrugRequestDTO {
    private Long id;
    private Long drugID;
    private String drugName;
    private Long pharmacyID;
    private String userEmail;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private UserRoles userRole;

    private Long appointmentID;

    public DrugRequestDTO() {
    }

    public DrugRequestDTO(Long id, Long drugID, String drugName,
                          Long pharmacyID, String userEmail, UserRoles userRole, Long appointmentID) {
        this.id = id;
        this.drugID = drugID;
        this.drugName = drugName;
        this.pharmacyID = pharmacyID;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.appointmentID = appointmentID;
    }

    public static DrugRequestDTO request2dto(DrugNotInPharmacyStashRequest drugRequest) {
        DrugRequestDTO dto = new DrugRequestDTO(
                drugRequest.getId(),
                drugRequest.getDrug().getId(),
                drugRequest.getDrug().getName(),
                drugRequest.getPharmacy().getId(),
                drugRequest.getUser().getEmail(),
                drugRequest.getUser().getRole(),
                drugRequest.getAppointment().getId()
        );

        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDrugID() {
        return drugID;
    }

    public void setDrugID(Long drugID) {
        this.drugID = drugID;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Long getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(Long pharmacyID) {
        this.pharmacyID = pharmacyID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
    }

    public Long getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(Long appointmentID) {
        this.appointmentID = appointmentID;
    }
}
