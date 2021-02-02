package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import isa.project.pharmacyapp.model.AbsenceRequest;

import java.math.BigInteger;
import java.util.Date;

public class AbsenceRequestDTO {
    private Long id;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date endDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date startDate;
    private Boolean status;
    private Long pharmacyId;
    private Long userId;
    private String userEmail;

    public AbsenceRequestDTO() {
    }

    public AbsenceRequestDTO(Long id, String description,
                             Date endDate, Date startDate, Boolean status, Long pharmacyId, Long userId, String userEmail) {
        this.id = id;
        this.description = description;
        this.endDate = endDate;
        this.startDate = startDate;
        this.status = status;
        this.pharmacyId = pharmacyId;
        this.userId = userId;
        this.userEmail = userEmail;
    }


    public static AbsenceRequestDTO request2DTO(AbsenceRequest absenceRequest) {
        AbsenceRequestDTO dto = new AbsenceRequestDTO(
                absenceRequest.getId(),
                absenceRequest.getDescription(),
                absenceRequest.getEndDate(),
                absenceRequest.getStartDate(),
                absenceRequest.getStatus(),
                absenceRequest.getPharmacy().getId(),
                absenceRequest.getUser().getId(),
                absenceRequest.getUser().getEmail()
        );

        return dto;

    }

    public static void dto2request(AbsenceRequest absenceRequest, AbsenceRequestDTO absenceRequestDTO) {
        absenceRequest.setDescription(absenceRequestDTO.getDescription());
        absenceRequest.setEndDate(absenceRequestDTO.getEndDate());
        absenceRequest.setStartDate(absenceRequestDTO.getStartDate());
        absenceRequest.setStatus(absenceRequestDTO.getStatus());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
