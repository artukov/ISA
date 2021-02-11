package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.util.Date;

public class PharmaDermaDTO {

    private Integer hours;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date start_hour;
    private Long pharmacy_id;
    private Long derma_id;
    private String pharmaName;

    public String getPharmaName() {
        return pharmaName;
    }

    public void setPharmaName(String pharmaName) {
        this.pharmaName = pharmaName;
    }

    public PharmaDermaDTO() {
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Date getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(Date start_hour) {
        this.start_hour = start_hour;
    }

    public Long getPharmacy_id() {
        return pharmacy_id;
    }

    public void setPharmacy_id(Long pharmacy_id) {
        this.pharmacy_id = pharmacy_id;
    }

    public Long getDerma_id() {
        return derma_id;
    }

    public void setDerma_id(Long derma_id) {
        this.derma_id = derma_id;
    }
}
