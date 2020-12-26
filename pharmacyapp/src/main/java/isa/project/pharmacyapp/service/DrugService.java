package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.DrugDTO;
import isa.project.pharmacyapp.model.TimeSpam;

import java.util.List;

public interface DrugService {

    public DrugDTO findById(Long id);
    public List<DrugDTO> findAll();
    public List<DrugDTO> findAllPharmacyDrugs(Long pharmacyId);
    public List<DrugDTO> findAllPatientDrugs(Long patientId);
    public void createNewDrug(DrugDTO drugDTO) throws Exception;
    public void modifyDrug(Long id, DrugDTO drugDTO) throws Exception;
    public void deleteDrug(Long id) throws Exception;

    List<Double> getConsumptionStatistics(TimeSpam timeSpam, Long pharmacyID);
}
