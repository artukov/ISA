package isa.project.pharmacyapp.strategy_pattern;

import isa.project.pharmacyapp.model.Appointment;
import isa.project.pharmacyapp.model.Calendar;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.repository.CalendarRepository;
import isa.project.pharmacyapp.repository.DrugRepository;
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

    @Autowired
    private DrugRepository drugRepository;

    @Override
    public List<Double> calculateStatistics(Pharmacy pharmacy) {

        ArrayList<Double> stats = new ArrayList<>();

        List<Double> years = repository.getAllYears(pharmacy.getCalendar().getId());

        for(Double year : years){
            for(int month = 1; month < 13; month++){
                stats.add(repository.getMonthlyExaminations(pharmacy.getCalendar().getId(), month, year));
            }
        }

        return stats;
    }

    @Override
    public List<Double> calculateDrugStatistics(Pharmacy pharmacy) {
        ArrayList<Double> stats = new ArrayList<>();
        List<Double> years = repository.getAllYears(pharmacy.getCalendar().getId());

        for(Double year : years){
           for(int month = 1; month < 13; month++){
               stats.add(drugRepository.getMonthlyConsumptionStatistics(pharmacy.getCalendar().getId(), month, year));
           }
        }
        return stats;
    }
}
