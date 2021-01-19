package isa.project.pharmacyapp.dto;

import java.math.BigInteger;
import java.util.Date;

public class CalendarDTO {

    private BigInteger calendar_id;
    private BigInteger appointment_id;
    private Date date;

    public CalendarDTO(BigInteger calendar_id, BigInteger appointment_id, Date date) {
        this.calendar_id = calendar_id;
        this.appointment_id = appointment_id;
        this.date = date;
    }

    public CalendarDTO() {
    }

    public BigInteger getCalendar_id() {
        return calendar_id;
    }

    public void setCalendar_id(BigInteger calendar_id) {
        this.calendar_id = calendar_id;
    }

    public BigInteger getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(BigInteger appointment_id) {
        this.appointment_id = appointment_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
