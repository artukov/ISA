package isa.project.pharmacyapp.strategy_pattern;

import isa.project.pharmacyapp.model.Calendar;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("QUARTERLYStatistics")
public class QuarterlyStatistics implements StatisticsStrategy {

    @Autowired
    private CalendarRepository repository;

    @Override
    public List<Double> calculateStatistics(Pharmacy pharmacy) {
        Calendar calendar = pharmacy.getCalendar();

        ArrayList<Double> stats = new ArrayList<>();

        List<Double> years = repository.getAllYears(calendar.getId());

        for(Double year : years){
            for(int i = 1; i < 13; i+=3){
                stats.add(repository.getQuarterlyExaminations(calendar.getId(),i,year));
            }
        }

        return stats;

    }
}
