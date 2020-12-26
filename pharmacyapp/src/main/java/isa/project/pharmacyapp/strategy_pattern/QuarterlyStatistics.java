package isa.project.pharmacyapp.strategy_pattern;

import isa.project.pharmacyapp.model.Calendar;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.repository.CalendarRepository;
import isa.project.pharmacyapp.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("QUARTERLYStatistics")
public class QuarterlyStatistics implements StatisticsStrategy {

    @Autowired
    private CalendarRepository repository;

    @Autowired
    private DrugRepository drugRepository;

    @Override
    public List<Double> calculateStatistics(Pharmacy pharmacy) {
        Calendar calendar = pharmacy.getCalendar();

        ArrayList<Double> stats = new ArrayList<>();

        List<Double> years = repository.getAllYears(calendar.getId());

        for(Double year : years){
            for(int month = 1; month < 13; month+=4){
                stats.add(repository.getQuarterlyExaminations(calendar.getId(),month,year));
            }
        }

        return stats;

    }

    @Override
    public List<Double> calculateDrugStatistics(Pharmacy pharmacy) {
        ArrayList<Double> stats = new ArrayList<>();

        List<Double> years = repository.getAllYears(pharmacy.getCalendar().getId());

        for(Double year : years){
            for(int month = 1; month < 13; month+=4){
                stats.add(drugRepository.getQuarterlyDrugConsumptionStatistics(pharmacy.getCalendar().getId(), month, year));
            }
        }

        return stats;
    }
}
