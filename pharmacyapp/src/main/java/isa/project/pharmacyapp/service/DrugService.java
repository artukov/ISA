package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.DrugDTO;
import isa.project.pharmacyapp.dto.DrugSpecDTO;
import isa.project.pharmacyapp.dto.DrugsFromReceiptPriceinPharmacyDTO;
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
    public List<DrugSpecDTO> getDrugSpec(String drugName);
    public void createNewDrug(DrugDTO drugDTO) throws Exception;
    public void modifyDrug(Long id, DrugDTO drugDTO) throws Exception;
    public void deleteDrug(Long id, Long pharmacyID) throws Exception;

    void saveDrug(Drug drug, DrugDTO drugDTO) throws Exception;

    List<Double> getConsumptionStatistics(TimeSpam timeSpam, Long pharmacyID);
    List<Long> findSubstituteDrug(Long drugId);
    List<Long> getDrugsFromEReceipt(Long receiptId);
    void addToPharmacyDrug(DrugDTO drugDTO, Long pharmacyID) throws Exception;
    List<DrugsFromReceiptPriceinPharmacyDTO> getPharmaciesWithDrugs(Long eReceiptId) throws Exception;
    boolean drugExistsInPharmacy(Long drugID, Long pharmacyID);

    void addToPharmacyDrug(Drug drug, Long pharmacyID, Integer amount) throws Exception;

    List<DrugDTO> findAllNotPharmacyDrugs(Long pharmacyID) throws Exception;
    List<DrugDTO> checkForAllergy(Long patientId, Long drugId);
    List<DrugDTO> getPatientDrugsFromReservation(Long patientId);
    List<DrugDTO> getPatientDrugsFromEReceipt(Long patientId);
}
