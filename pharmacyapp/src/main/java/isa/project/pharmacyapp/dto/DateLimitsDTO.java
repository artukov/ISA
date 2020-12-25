package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DateLimitsDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy ss:mm:HH Z")
    private Date lowerLimit;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy ss:mm:HH Z")
    private Date upperLimit;

    public DateLimitsDTO() {
    }

    public DateLimitsDTO(Date lowerLimit, Date upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public Date getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Date lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Date getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Date upperLimit) {
        this.upperLimit = upperLimit;
    }
}
