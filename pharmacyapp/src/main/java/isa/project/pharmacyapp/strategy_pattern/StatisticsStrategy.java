package isa.project.pharmacyapp.strategy_pattern;

import isa.project.pharmacyapp.model.Pharmacy;

import java.util.List;

public interface StatisticsStrategy {

    List<Double> calculateStatistics(Pharmacy pharmacy);
    List<Double> calculateDrugStatistics(Pharmacy pharmacy);
}
