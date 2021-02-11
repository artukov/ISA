package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PatConsDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    public Date appointmentDate;
    public String firstName;
    public String lastName;
    public String email;

    public PatConsDTO() {
    }

    public PatConsDTO(Date appointmentDate, String firstName, String lastName, String email) {
        this.appointmentDate = appointmentDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
