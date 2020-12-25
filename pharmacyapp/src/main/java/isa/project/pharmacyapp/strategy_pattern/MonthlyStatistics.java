package isa.project.pharmacyapp.strategy_pattern;

import isa.project.pharmacyapp.model.Appointment;
import isa.project.pharmacyapp.model.Calendar;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Component("MONTHLYStatistics")
public class MonthlyStatistics implements StatisticsStrategy {

    @Autowired
    private CalendarRepository repository;

    @Override
    public List<Double> calculateStatistics(Pharmacy pharmacy) {

        Calendar calendar = pharmacy.getCalendar();
//        List<Timestamp> dates = repository.getDateExaminations(calendar.getId());

        ArrayList<Double> stats = new ArrayList<>();

        List<Double> years = repository.getAllYears(calendar.getId());

        for(Double year : years){

            for(int i = 1; i < 13; i++){
                stats.add(repository.getMonthlyExaminations(calendar.getId(), i, year));
            }
        }

        return stats;
    }
}
