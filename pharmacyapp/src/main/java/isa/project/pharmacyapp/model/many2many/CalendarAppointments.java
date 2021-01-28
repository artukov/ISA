package isa.project.pharmacyapp.model.many2many;

import isa.project.pharmacyapp.model.embedded_ids.CalendarAppointmentsID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "calendar_appointments")
public class CalendarAppointments {

    @EmbeddedId
    private CalendarAppointmentsID id;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    public CalendarAppointments() {
    }

    public CalendarAppointmentsID getId() {
        return id;
    }

    public void setId(CalendarAppointmentsID id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
