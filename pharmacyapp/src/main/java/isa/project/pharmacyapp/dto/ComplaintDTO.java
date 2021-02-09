package isa.project.pharmacyapp.dto;

import isa.project.pharmacyapp.model.Complaints;

public class ComplaintDTO {
    private Long id;
    private Long submitter;
    private String submitterEmail;
    private Long referred;
    private String referredEmail;
    private Long pharmacyID;
    private String pharmacyName;
    private String description;
    private String answer;

    public ComplaintDTO() {
    }

    public ComplaintDTO(Long id, Long submitter, String submitterEmail,
                        Long referred, String referredEmail, Long pharmacyID, String pharmacyName,
                        String description, String answer) {
        this.id = id;
        this.submitter = submitter;
        this.submitterEmail = submitterEmail;
        this.referred = referred;
        this.referredEmail = referredEmail;
        this.pharmacyID = pharmacyID;
        this.pharmacyName = pharmacyName;
        this.description = description;
        this.answer = answer;
    }

    public static ComplaintDTO complaint2dto(Complaints complaints) {
        ComplaintDTO dto = new ComplaintDTO(
                complaints.getId(),
                complaints.getSubmitter().getId(),
                complaints.getSubmitter().getEmail(),
                null,
                null,
                null,
                null,
                complaints.getDescription(),
                complaints.getAnswer()
        );
        if(complaints.getPharmacy() != null){
            dto.setPharmacyID(complaints.getPharmacy().getId());
            dto.setPharmacyName(complaints.getPharmacy().getName());
        }
        else if(complaints.getReferred() != null){
            dto.setReferred(complaints.getReferred().getId());
            dto.setReferredEmail(complaints.getReferred().getEmail());
        }

        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubmitter() {
        return submitter;
    }

    public void setSubmitter(Long submitter) {
        this.submitter = submitter;
    }

    public Long getReferred() {
        return referred;
    }

    public void setReferred(Long referred) {
        this.referred = referred;
    }

    public Long getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(Long pharmacyID) {
        this.pharmacyID = pharmacyID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSubmitterEmail() {
        return submitterEmail;
    }

    public void setSubmitterEmail(String submitterEmail) {
        this.submitterEmail = submitterEmail;
    }

    public String getReferredEmail() {
        return referredEmail;
    }

    public void setReferredEmail(String referredEmail) {
        this.referredEmail = referredEmail;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}
