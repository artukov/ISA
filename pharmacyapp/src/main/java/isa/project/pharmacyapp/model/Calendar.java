package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  Calendar.java
 * Author:  User
 * Purpose: Defines the Class Calendar
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;
/** @pdOid e96db096-4921-4472-b1fd-bb4ddce28df4 */
@Entity
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(fetch = FetchType.LAZY, targetClass = GregorianCalendar.class)
    private List<GregorianCalendar> dates;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "calendar_appointments",
            joinColumns = @JoinColumn(name = "calendar_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    )
    private List<Appointment> appointments;

    public Calendar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GregorianCalendar> getDates() {
        return dates;
    }

    public void setDates(List<GregorianCalendar> dates) {
        this.dates = dates;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}