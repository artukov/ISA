package isa.project.pharmacyapp.dto;

import java.math.BigInteger;
import java.util.Date;

public class AbsenceRequestDTO {
    private Long id;
    private String description;
    private Date endDate;
    private Date startDate;
    private Boolean status = false;
    private Long pharmacyId;
    private Long userId;

    public AbsenceRequestDTO() {
    }

    public AbsenceRequestDTO(Long id, String description, Date endDate, Date startDate, Boolean status, Long pharmacyId, Long userId) {
        this.id = id;
        this.description = description;
        this.endDate = endDate;
        this.startDate = startDate;
        this.status = status;
        this.pharmacyId = pharmacyId;
        this.userId = userId;
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
}
