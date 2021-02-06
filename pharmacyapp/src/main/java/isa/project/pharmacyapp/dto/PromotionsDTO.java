package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import isa.project.pharmacyapp.model.Promotions;

import java.util.Date;

public class PromotionsDTO {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date endDate;
    private Long pharmacyID;
    private String content;

    public PromotionsDTO(Long id, Date startDate, Date endDate, Long pharmacyID, String content) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pharmacyID = pharmacyID;
        this.content = content;
    }

    public PromotionsDTO() {
    }

    public static void dto2promotions(Promotions promotions, PromotionsDTO promotionsDTO) {
        promotions.setContent(promotionsDTO.getContent());
        promotions.setEndDate(promotionsDTO.getEndDate());
        promotions.setStartDate(promotionsDTO.getStartDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(Long pharmacyID) {
        this.pharmacyID = pharmacyID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
