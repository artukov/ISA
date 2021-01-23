package isa.project.pharmacyapp.exception;

import java.util.Date;

public class ExaminationOverlappingException extends RuntimeException{

    private Date begDateTime;
    private Date endDateTime;

    public ExaminationOverlappingException(String message, Date begDateTime, Date endDateTime) {
        super(message);
        this.begDateTime = begDateTime;
        this.endDateTime = endDateTime;
    }

    public Date getBegDateTime() {
        return begDateTime;
    }

    public void setBegDateTime(Date begDateTime) {
        this.begDateTime = begDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }
}
