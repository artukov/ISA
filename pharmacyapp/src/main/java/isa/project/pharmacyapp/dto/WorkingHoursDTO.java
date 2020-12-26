package isa.project.pharmacyapp.dto;

import java.util.Date;

public class WorkingHoursDTO {

    private Date startHour;
    private Integer hours;

    public WorkingHoursDTO() {
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
