package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.PlDrugDTO;
import isa.project.pharmacyapp.dto.PriceListDTO;
import isa.project.pharmacyapp.exception.DrugNotExistsException;
import isa.project.pharmacyapp.exception.DrugNotExistsInPharmacyException;
import isa.project.pharmacyapp.exception.PharmacyNotExistsException;
import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.model.PriceList;
import isa.project.pharmacyapp.model.embedded_ids.PriceListDrugID;
import isa.project.pharmacyapp.model.many2many.PriceListDrug;
import isa.project.pharmacyapp.repository.DrugRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.repository.PriceListRepository;
import isa.project.pharmacyapp.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceListServiceImpl implements PriceListService {

    @Autowired
    private PriceListRepository priceListRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private DrugRepository drugRepository;


    @Override
    public PriceListDTO findLatestPharmacyPriceList(Long pharmacyID) {
        PriceList priceList = priceListRepository.findLatest(pharmacyID);
        return  PriceListDTO.priceList2DTO(priceList);
    }

    @Override
    public List<PriceListDTO> findAllPharmacy(Long pharmacyID) {
        List<PriceList> priceLists = priceListRepository.findByPharmacy_id(pharmacyID);
        ArrayList<PriceListDTO> dtos = new ArrayList<>();
        for(PriceList priceList : priceLists){
            dtos.add(PriceListDTO.priceList2DTO(priceList));
        }

        return dtos;
    }

    @Override
    public void creatNewPriceList(PriceListDTO dto) throws Exception {
        PriceList priceList = new PriceList();
        priceList.setDrugs(new ArrayList<>());
        savePriceList(priceList,dto);


    }

    @Override
    public void modifyPriceList(Long id, PriceListDTO priceListDTO) throws Exception {

        PriceList priceList = priceListRepository.findById(id).orElse(null);
        if(priceList == null){
            throw new Exception("Price list with given id does not exists");
        }

        PriceList newPriceList = new PriceList();
        newPriceList.setDrugs(new ArrayList<>());

        if(priceList.getStartDate().equals(priceListDTO.getStartDate()) &&
                priceList.getEndDate().equals(priceListDTO.getEndDate())){

            newPriceList.setEndDate(priceList.getEndDate());
            newPriceList.setStartDate(priceList.getStartDate());
            newPriceList.setDrugs(priceList.getDrugs());
        }

        priceList.setActive(false);
        priceListRepository.save(priceList);


        savePriceList(newPriceList,priceListDTO);
    }



    @Override
    public void savePriceList(PriceList priceList, PriceListDTO priceListDTO) throws Exception {
        PriceListDTO.dto2PriceList(priceList,priceListDTO);

        Pharmacy pharmacy = pharmacyRepository.findById(priceListDTO.getPharmacyID()).orElse(null);
        if(pharmacy == null){
            throw  new PharmacyNotExistsException(PharmacyNotExistsException.message);
        }

        priceList.setPharmacy(pharmacy);

//        ArrayList<PriceListDrug> drugs = new ArrayList<>();

        for(PlDrugDTO plDrugDTO : priceListDTO.getDrugs()){
            if(drugRepository.findInPharmacy(pharmacy.getId(), plDrugDTO.getDrugID()) == 0.0){
                throw new DrugNotExistsInPharmacyException(DrugNotExistsInPharmacyException.message);
            }
            Drug drug = drugRepository.findById(plDrugDTO.getDrugID()).orElse(null);
            if(drug == null){
                throw new DrugNotExistsException(DrugNotExistsException.message);
            }
            PriceListDrug priceListDrug = new PriceListDrug();

            PriceListDrugID id = new PriceListDrugID();
            id.setPriceList(priceList);
            id.setDrug(drug);

            priceListDrug.setId(id);
            priceListDrug.setPrice(plDrugDTO.getPrice());

            priceList.getDrugs().add(priceListDrug);
        }

//        priceList.setDrugs(drugs);
        priceList.setActive(true);

        try{
            PriceList p = priceListRepository.save(priceList);
//            updateActiveStatus(p.getId(),pharmacy.getId());
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("Saving price list");
        }

    }

    @Transactional
    @Override
    public void updateActiveStatus(Long plID, Long pharmacyID) throws Exception {

        try{
            priceListRepository.updateActiveStatus(plID,pharmacyID);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
