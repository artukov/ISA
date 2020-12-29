package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.DrugDTO;
import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.model.many2many.PharmacyDrug;
import isa.project.pharmacyapp.model.TimeSpam;
import isa.project.pharmacyapp.model.embedded_ids.PharmacyDrugID;
import isa.project.pharmacyapp.repository.DrugRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.repository.ReservationRepository;
import isa.project.pharmacyapp.service.DrugService;
import isa.project.pharmacyapp.service.bean_factory_statistics.BeanFactoryDynamicService;
import isa.project.pharmacyapp.strategy_pattern.StatisticsStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private BeanFactoryDynamicService beanFactoryDynamicService;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    final private static String EXCEPTION_TEXT = "DrugServiceImpl::";
    final private static String DOES_NOT_EXISTS = "drug with given id does not exists";
    final private static String UNSUCCESSFUL_EXECUTION = "did not execute successfully";

    @Override
    public DrugDTO findById(Long id) {
        Drug drug = drugRepository.findById(id).orElse(null);
        if(drug == null){
            throw  new NoSuchElementException(EXCEPTION_TEXT + "findById" + DOES_NOT_EXISTS);
        }
        return DrugDTO.drug2DTO(drug);
    }

    @Override
    public List<DrugDTO> findAll() {
        List<Drug> drugs = drugRepository.findAll();

        return this.listCreationDrug2DTO(drugs);
    }

    @Override
    public List<DrugDTO> findAllPharmacyDrugs(Long pharmacyId) {
       List<Drug> drugs =  drugRepository.findAllByPharmacyId(pharmacyId);


       return this.listCreationDrug2DTO(drugs);
    }

    @Override
    public List<DrugDTO> findAllPatientDrugs(Long patientId) {
        return null;
    }

    @Override
    public void createNewDrug(DrugDTO drugDTO) throws Exception {

        Drug drug = new Drug();

        try {
            this.saveDrug(drug,drugDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new Exception(EXCEPTION_TEXT + "createNewDrug creating new" + UNSUCCESSFUL_EXECUTION);
        }

    }

    @Override
    public void modifyDrug(Long id, DrugDTO drugDTO) throws Exception {
        Drug drug = drugRepository.findById(id).orElse(null);

        if(drug == null){
            throw new NoSuchElementException(EXCEPTION_TEXT + "modifyDrug" + DOES_NOT_EXISTS);
        }

        try {
            this.saveDrug(drug, drugDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(EXCEPTION_TEXT + "modifyDrug saving drug" + UNSUCCESSFUL_EXECUTION);
        }

    }

    @Transactional
    @Override
    public void deleteDrug(Long id, Long pharmacyID) throws Exception {

        Drug drug = drugRepository.findById(id).orElse(null);
        if(drug == null){
            throw  new Exception(EXCEPTION_TEXT + "deleteDrug" + DOES_NOT_EXISTS);
        }

        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyID).orElse(null);
        if(pharmacy == null){
            throw new Exception(EXCEPTION_TEXT + "delete drug pharmacy does not exists");
        }

        /**
         * TODO
         * Check if the drug reserved
         * */
        if(reservationRepository.countReservedDrugsInPharmacy(id, pharmacyID) == 0.0){
            throw  new Exception("Drug is reserved");
        }

        drugRepository.deleteDrug(id);

    }

    @Override
    public void saveDrug(Drug drug, DrugDTO drugDTO) throws Exception {
        DrugDTO.dto2Drug(drug,drugDTO);

        for(Long id : drugDTO.getSubstituteDrugs()){
            try{
                drug.getSubstituteDrugs().add(drugRepository.findById(id).orElse(null));
            }catch (Exception e){
                e.printStackTrace();
                throw new Exception(EXCEPTION_TEXT + "saveDrug" + DOES_NOT_EXISTS);
            }
        }
        try{
            drugRepository.save(drug);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception(EXCEPTION_TEXT + "saveDrug saving drug" + UNSUCCESSFUL_EXECUTION);
        }
    }

    @Override
    public List<Double> getConsumptionStatistics(TimeSpam timeSpam, Long pharmacyID) {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyID).orElse(null);
        /**
         * TODO
         * if pharmacy id is not valid exemption
         * */
        StatisticsStrategy strategy = beanFactoryDynamicService.getStrategyStatistics(timeSpam);

        return strategy.calculateDrugStatistics(pharmacy);
    }

    @Transactional //if we are using update/delete operations, needed annotation
    @Override
    public void addToPharmacyDrug(DrugDTO drugDTO, Long pharmacyID) throws Exception {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyID).orElse(null);
        if(pharmacy == null){
            throw new Exception("pharmacy not found");
        }
        Drug drug = drugRepository.findById(drugDTO.getId()).orElse(null);
        if(drug == null){
            throw new Exception("drug not found");
        }

        if(drugDTO.getAmount() == null){
            drugDTO.setAmount(0);
        }

        /**
         * If drug does not exists in the pharmacy than we need to create a new row in the database
         * pharmacy_drug table
         * */
        if(drugRepository.findInPharmacy(pharmacyID, drug.getId()) == 0.0 ){
            List<PharmacyDrug> pharmacyDrugs = drug.getPharmacies();

            /**
             * Inserting new row into pharmacy_drug table
             * Need new instances of {@code PharmacyDrug} and {@code PharmacyDrugID} classes
             * */

            PharmacyDrug pd = new PharmacyDrug();

            /**
             * Setting the ids
             * */
            PharmacyDrugID id = new PharmacyDrugID();
            id.setPharmacy(pharmacy);
            id.setDrug(drug);

            pd.setId(id);
            pd.setAmount(drugDTO.getAmount());

            pharmacyDrugs.add(pd);

            drugRepository.save(drug);
        }
        else{
            /**
             * Update database if the relationship already exists between pharmacy and drug
             * */
            drugRepository.addDrugToPharmacy(drugDTO.getId(), pharmacyID, drugDTO.getAmount());
        }



    }

    private List<DrugDTO> listCreationDrug2DTO(List<Drug> drugs){
        ArrayList<DrugDTO> drugDTOS = new ArrayList<>();
        for(Drug drug : drugs){
            drugDTOS.add(DrugDTO.drug2DTO(drug));
        }

        return  drugDTOS;
    }


}
