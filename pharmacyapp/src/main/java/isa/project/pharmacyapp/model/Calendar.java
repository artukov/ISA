package isa.project.pharmacyapp.model;
/***********************************************************************
 * Module:  Calendar.java
 * Author:  User
 * Purpose: Defines the Class Calendar
 ***********************************************************************/

import isa.project.pharmacyapp.model.many2many.CalendarAppointments;

import java.util.*;
import javax.persistence.*;
/** @pdOid e96db096-4921-4472-b1fd-bb4ddce28df4 */
@Entity
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(fetch = FetchType.LAZY, targetClass = Date.class)
    private List<Date> dates;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "calendar_appointments",
//            joinColumns = @JoinColumn(name = "calendar_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id")
//    )
//    private List<Appointment> appointments;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "calendar_id")
    private List<CalendarAppointments> appointments;

    public Calendar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public List<CalendarAppointments> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<CalendarAppointments> appointments) {
        this.appointments = appointments;
    }
}