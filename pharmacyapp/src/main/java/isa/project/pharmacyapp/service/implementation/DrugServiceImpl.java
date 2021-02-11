package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.*;
import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.DrugShapes;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.model.many2many.PharmacyDrug;
import isa.project.pharmacyapp.model.TimeSpam;
import isa.project.pharmacyapp.model.embedded_ids.PharmacyDrugID;
import isa.project.pharmacyapp.repository.DrugRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.repository.PriceListRepository;
import isa.project.pharmacyapp.repository.ReservationRepository;
import isa.project.pharmacyapp.service.DrugService;
import isa.project.pharmacyapp.service.bean_factory_statistics.BeanFactoryDynamicService;
import isa.project.pharmacyapp.strategy_pattern.StatisticsStrategy;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private PriceListRepository priceListRepository;


    final private static String EXCEPTION_TEXT = "DrugServiceImpl::";
    final private static String DOES_NOT_EXISTS = "drug with given id does not exists";
    final private static String UNSUCCESSFUL_EXECUTION = "did not execute successfully";

    @Override
    public DrugDTO findById(Long id) {
        Drug drug = drugRepository.findById(id).orElse(null);
        if(drug == null){
            throw  new NoSuchElementException(EXCEPTION_TEXT + "findById" + DOES_NOT_EXISTS);
        }

        return  DrugDTO.drug2DTO(drug);
    }

    @Override
    public List<DrugDTO> findAll() {
        List<Drug> drugs = drugRepository.findAll();

        return this.listCreationDrug2DTO(drugs, null);
    }

    @Override
    public List<PharmaDrugDTO> searchDrugs(String drugName){
        List<Object[]> drugs = this.drugRepository.searchDrugs(drugName);

        ArrayList<PharmaDrugDTO> drugDTOs = new ArrayList<>();
        for(Object[] obj : drugs){
            PharmaDrugDTO dto = new PharmaDrugDTO();

//            dto.setName((Big) obj[0]);
//            dto.setType((String) obj[1]);
            dto.setPharmacy_id((BigInteger) obj[0]);
            dto.setPrice((Double) obj[1]);
            dto.setPharmaName((String) obj[2]);
            drugDTOs.add(dto);
        }
        return drugDTOs;
    }

    @Override
    public List<DrugSpecDTO> getDrugSpec(String drugName){

        List<Object[]> specs = this.drugRepository.getDrugSpec(drugName);

        ArrayList<DrugSpecDTO> dtos = new ArrayList<>();

        for(Object[] obj : specs){
            DrugSpecDTO dto = new DrugSpecDTO();

            dto.setId((BigInteger) obj[0]);
            dto.setCode((BigInteger) obj[1]);
            dto.setManufacturer((String) obj[2]);
            dto.setName((String) obj[3]);
//            switch (obj[4]){
//                case 0:
//                    dto.setShape();
//                case 1:
//                    dto.setShape();
//                case 2:
//                case 3:
//            }
            dto.setShape(DrugShapes.values() [(Integer) obj[4]]);
            dto.setType((String) obj[5]);
            dto.setComposition((String) obj[6]);
            dto.setRecom_consum((String) obj[7]);
            dto.setSide_effects((String) obj[8]);

            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<DrugDTO> findAllPharmacyDrugs(Long pharmacyId) {
       List<Drug> drugs =  drugRepository.findAllByPharmacyId(pharmacyId);


       return this.listCreationDrug2DTO(drugs, pharmacyId);
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

//        Drug drug = drugRepository.findById(id).orElse(null);
//        if(drug == null){
//            throw  new Exception(EXCEPTION_TEXT + "deleteDrug" + DOES_NOT_EXISTS);
//        }
//
//        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyID).orElse(null);
//        if(pharmacy == null){
//            throw new Exception(EXCEPTION_TEXT + "delete drug pharmacy does not exists");
//        }

        if(!drugRepository.existsById(id))
            throw  new Exception(EXCEPTION_TEXT + "deleteDrug" + DOES_NOT_EXISTS);

        if(!pharmacyRepository.existsById(pharmacyID))
            throw new Exception(EXCEPTION_TEXT + "delete drug pharmacy does not exists");


        if(reservationRepository.countReservedDrugsInPharmacy(id, pharmacyID) != 0.0){
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
        if(!this.drugExistsInPharmacy(drugDTO.getId(), pharmacyID)){
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

    @Transactional
    @Override
    public void addToPharmacyDrug(Drug drug, Long pharmacyID, Integer amount) throws Exception {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyID).orElse(null);
        if(pharmacy == null){
            throw new Exception("pharmacy not found");
        }
        /**
         * If drug does not exists in the pharmacy than we need to create a new row in the database
         * pharmacy_drug table
         * */
        if(!this.drugExistsInPharmacy(drug.getId(), pharmacyID)){
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
            pd.setAmount(amount);

            pharmacyDrugs.add(pd);

            drugRepository.save(drug);
        }
        else{
            /**
             * Update database if the relationship already exists between pharmacy and drug
             * */

            drugRepository.addDrugToPharmacy(drug.getId(), pharmacyID, amount);
        }
    }

    @Override
    public List<DrugDTO> findAllNotPharmacyDrugs(Long pharmacyID) throws Exception {
        if(!pharmacyRepository.existsById(pharmacyID)){
            throw new Exception("There is no pharmacy with given id");
        }

        List<Drug> drugs = drugRepository.findAllNoInPharmacy(pharmacyID);

        ArrayList<DrugDTO> drugDTOS = new ArrayList<>();

        for(Drug drug : drugs){
            drugDTOS.add(DrugDTO.drug2DTO(drug));
        }

        return drugDTOS;
    }
    public List<DrugDTO> checkForAllergy(Long patientId, Long drugId){
        ArrayList<DrugDTO> drugDTOS = new ArrayList<>();
        if(drugRepository.checkForAllergy(patientId,drugId).isEmpty()){
            return drugDTOS;
        }
        List<Drug> drugs = drugRepository.checkForAllergy(patientId,drugId);
        for(Drug drug: drugs){
            drugDTOS.add(DrugDTO.drug2DTO(drug));
        }
        return drugDTOS;
    }

    /**
     * Returns true if the drug exists in pharmacy records
     * Returns false if the other case
     * @param drugID
     * @param pharmacyID
     * */
    @Override
    public boolean drugExistsInPharmacy(Long drugID, Long pharmacyID) {

        if(drugRepository.findInPharmacy(pharmacyID, drugID) == 0.0 ){
            return false;
        }
        return true;
    }



    private List<DrugDTO> listCreationDrug2DTO(List<Drug> drugs, Long pharmacyID){
        ArrayList<DrugDTO> drugDTOS = new ArrayList<>();
        Integer amount  = null;
        for(Drug drug : drugs){
            DrugDTO drugDTO = DrugDTO.drug2DTO(drug);
            if(pharmacyID != null){
                amount = drugRepository.getAmount(drug.getId(), pharmacyID);
                drugDTO.setAmount(amount);
            }
            drugDTOS.add(drugDTO);
        }

        return  drugDTOS;
    }
    @Override
     public List<Long> findSubstituteDrug(Long drugId){
        List<Long> drugIds = drugRepository.findSubstituteDrug(drugId);

        return drugIds;
    }

    @Override
    public List<Long> getDrugsFromEReceipt(Long receiptId){
        List<Long> drugIds = drugRepository.getDrugsFromEReceipt(receiptId);

        return drugIds;
    }

    @Override
    public List<DrugsFromReceiptPriceinPharmacyDTO> getPharmaciesWithDrugs(Long eReceiptId) throws Exception {
        List<DrugsFromReceiptPriceinPharmacyDTO> pharmacies = new ArrayList<>();
        List<Long> drugsId = drugRepository.getDrugsFromEReceipt(eReceiptId);

        for(Pharmacy pharmacy: pharmacyRepository.findAll()){
            //List<PharmacyDrug> allDrugsInPharmacy = pharmacy.getDrugs();
            List<Long> allDrugsInPharmacy = drugRepository.getDrugsIdByPharmacy(pharmacy.getId());
            DrugsFromReceiptPriceinPharmacyDTO dto = new DrugsFromReceiptPriceinPharmacyDTO();
            Double totalPrice = 0.0;
           if(allDrugsInPharmacy.containsAll(drugsId)){
               if(priceListRepository.findByPharmacy_id(pharmacy.getId()) == null){
                   throw new Exception("Pharmacy doesnt have priceList");
               }
               dto.setId(pharmacy.getId());
               dto.setName(pharmacyRepository.getOne(pharmacy.getId()).getName());
               for(Long drugId: drugsId) {
                   totalPrice += priceListRepository.getDrugPriceByPharmacyPriceList(pharmacy.getId(),drugId);
               }
               dto.setTotalPrice(totalPrice);
               pharmacies.add(dto);
           }
        }
        return pharmacies;
    }

    @Override
    public List<DrugDTO> getPatientDrugsFromReservation(Long patientId){
        List<Drug> drugs = drugRepository.getPatientsDrugsFromReservation(patientId);
        List<DrugDTO> dtos = new ArrayList<>();

        for(Drug d: drugs){
            DrugDTO dto = new DrugDTO();
            dto.setName(d.getName());
            dto.setManufacturer(d.getManufacturer());
            dto.setType(d.getType());
            dto.setShape(d.getShape());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<DrugDTO> getPatientDrugsFromEReceipt(Long patientId){
        List<Drug> drugs = drugRepository.getPatientDrugsFromEReceipt(patientId);
        List<DrugDTO> dtos = new ArrayList<>();

        for(Drug d: drugs){
            DrugDTO dto = new DrugDTO();
            dto.setName(d.getName());
            dto.setManufacturer(d.getManufacturer());
            dto.setType(d.getType());
            dto.setShape(d.getShape());
            dtos.add(dto);
        }
        return dtos;
    }
}
