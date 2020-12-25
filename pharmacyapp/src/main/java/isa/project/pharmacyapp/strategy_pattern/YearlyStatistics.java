package isa.project.pharmacyapp.strategy_pattern;


import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("YEARLYStatistics")
public class YearlyStatistics implements StatisticsStrategy {

    @Autowired
    private CalendarRepository repository;

    @Override
    public List<Double> calculateStatistics(Pharmacy pharmacy) {

        return repository.getYearlyExaminations(pharmacy.getCalendar().getId());

    }
}
