package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.util.Date;

public class CalendarDTO {

    private BigInteger calendar_id;
    private BigInteger appointment_id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date date;
    private BigInteger pharmacy_id;

    public CalendarDTO(BigInteger calendar_id, BigInteger appointment_id, Date date, BigInteger pharmacy_id) {
        this.calendar_id = calendar_id;
        this.appointment_id = appointment_id;
        this.date = date;
        this.pharmacy_id = pharmacy_id;
    }

    public CalendarDTO() {
    }

    public BigInteger getPharmacy_id() {
        return pharmacy_id;
    }

    public void setPharmacy_id(BigInteger pharmacy_id) {
        this.pharmacy_id = pharmacy_id;
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
