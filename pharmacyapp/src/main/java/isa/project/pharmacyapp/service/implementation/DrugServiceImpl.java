package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.DrugDTO;
import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.repository.DrugRepository;
import isa.project.pharmacyapp.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    private DrugRepository drugRepository;


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

    @Override
    public void deleteDrug(Long id) throws Exception {

        Drug drug = drugRepository.findById(id).orElse(null);
        if(drug == null){
            throw  new Exception(EXCEPTION_TEXT + "deleteDrug" + DOES_NOT_EXISTS);
        }

        drugRepository.deleteDrug(id);

    }

    private List<DrugDTO> listCreationDrug2DTO(List<Drug> drugs){
        ArrayList<DrugDTO> drugDTOS = new ArrayList<>();
        for(Drug drug : drugs){
            drugDTOS.add(DrugDTO.drug2DTO(drug));
        }

        return  drugDTOS;
    }

    private void saveDrug(Drug drug, DrugDTO drugDTO) throws Exception {
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
}
