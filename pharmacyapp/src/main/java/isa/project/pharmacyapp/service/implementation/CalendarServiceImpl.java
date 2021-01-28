package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.model.Appointment;
import isa.project.pharmacyapp.model.Calendar;
import isa.project.pharmacyapp.model.Examination;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.model.embedded_ids.CalendarAppointmentsID;
import isa.project.pharmacyapp.model.many2many.CalendarAppointments;
import isa.project.pharmacyapp.repository.CalendarRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServiceImpl  implements CalendarService {

    @Autowired
    private CalendarRepository repository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Override
    public List<Double> getPharmacyCalendarYears(Long pharmacyID) {

        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyID).orElse(null);
        return repository.getAllYears(pharmacy.getCalendar().getId());
    }

    @Override
    public void saveAppointment(Long pharmacyID, Appointment appointment) throws Exception {

        Pharmacy pharmacy = pharmacyRepository.getOne(pharmacyID);
        Calendar calendar = pharmacy.getCalendar();

        CalendarAppointments calendarAppointments = new CalendarAppointments();

        CalendarAppointmentsID id = new CalendarAppointmentsID();
        id.setAppointment(appointment);
        id.setCalendar(calendar);

        calendarAppointments.setId(id);
        calendarAppointments.setAppointmentDate(appointment.getBeggingDateTime());

        calendar.getAppointments().add(calendarAppointments);
        calendar.getDates().add(appointment.getBeggingDateTime());

        pharmacy.setCalendar(calendar);


        try{
            pharmacyRepository.save(pharmacy);
        }
        catch(Exception e){
            e.printStackTrace();
            throw new Exception("Saving calendar appointments");

        }

    }
}
