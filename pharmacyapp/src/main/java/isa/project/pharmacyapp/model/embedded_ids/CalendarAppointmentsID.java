package isa.project.pharmacyapp.model.embedded_ids;

import isa.project.pharmacyapp.model.Appointment;
import isa.project.pharmacyapp.model.Calendar;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CalendarAppointmentsID implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "calendar_id", referencedColumnName = "id")
    private Calendar calendar;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;

    public CalendarAppointmentsID() {
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalendarAppointmentsID)) return false;
        CalendarAppointmentsID that = (CalendarAppointmentsID) o;
        return Objects.equals(calendar, that.calendar) && Objects.equals(appointment, that.appointment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calendar, appointment);
    }
}
