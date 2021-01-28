package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.model.Appointment;
import isa.project.pharmacyapp.model.Examination;

import java.util.List;


public interface CalendarService {
    List<Double> getPharmacyCalendarYears(Long pharmacyID);

    void saveAppointment(Long pharmacyID, Appointment appointment) throws Exception;
}
