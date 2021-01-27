package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.model.Pharmacy;
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
}
