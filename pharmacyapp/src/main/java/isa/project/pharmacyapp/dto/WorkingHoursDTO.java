package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class WorkingHoursDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date startHour;
    private Integer hours;

    public WorkingHoursDTO() {
    }

    public WorkingHoursDTO(Date startHour, Integer hours) {
        this.startHour = startHour;
        this.hours = hours;
    }

    public Date getStartHour() {
        return startHour;
    }

    public void setStartHour(Date startHour) {
        this.startHour = startHour;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
