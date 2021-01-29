package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.DrugDTO;
import isa.project.pharmacyapp.dto.DrugSpecDTO;
import isa.project.pharmacyapp.dto.PharmaDrugDTO;
import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.TimeSpam;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrugService {

    public DrugDTO findById(Long id);
    public List<DrugDTO> findAll();
    public List<DrugDTO> findAllPharmacyDrugs(Long pharmacyId);
    public List<DrugDTO> findAllPatientDrugs(Long patientId);
    public List<PharmaDrugDTO> searchDrugs(String drugName);
    public List<DrugSpecDTO> getDrugSpec(Long drugId);
    public void createNewDrug(DrugDTO drugDTO) throws Exception;
    public void modifyDrug(Long id, DrugDTO drugDTO) throws Exception;
    public void deleteDrug(Long id, Long pharmacyID) throws Exception;

    void saveDrug(Drug drug, DrugDTO drugDTO) throws Exception;

    List<Double> getConsumptionStatistics(TimeSpam timeSpam, Long pharmacyID);
    List<Long> findSubstituteDrug(Long drugId);
    void addToPharmacyDrug(DrugDTO drugDTO, Long pharmacyID) throws Exception;


    boolean drugExistsInPharmacy(Long drugID, Long pharmacyID);

    void addToPharmacyDrug(Drug drug, Long pharmacyID, Integer amount) throws Exception;

    List<DrugDTO> findAllNotPharmacyDrugs(Long pharmacyID) throws Exception;
}
