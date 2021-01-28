package isa.project.pharmacyapp.strategy_pattern;


import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.repository.CalendarRepository;
import isa.project.pharmacyapp.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("YEARLYStatistics")
public class YearlyStatistics implements StatisticsStrategy {

    @Autowired
    private CalendarRepository repository;

    @Autowired
    private DrugRepository drugRepository;

    @Override
    public List<Double> calculateStatistics(Pharmacy pharmacy) {
        return repository.getYearlyExaminations(pharmacy.getCalendar().getId());
    }

    @Override
    public List<Double> calculateDrugStatistics(Pharmacy pharmacy) {

        List<Double> years  = repository.getAllYears(pharmacy.getCalendar().getId());
        ArrayList<Double> count  = new ArrayList<>();
        for(Double year : years){
            count.add(drugRepository.getYearlyDrugConsumptionStatistics(pharmacy.getId(),year));
        }

        return count;
    }
}
