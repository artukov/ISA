package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.PriceListDTO;
import isa.project.pharmacyapp.exception.DrugNotExistsException;
import isa.project.pharmacyapp.exception.DrugNotExistsInPharmacyException;
import isa.project.pharmacyapp.exception.PharmacyNotExistsException;
import isa.project.pharmacyapp.model.PriceList;

import java.util.List;

public interface PriceListService {

    PriceListDTO findLatestPharmacyPriceList(Long pharmacyID);

    List<PriceListDTO> findAllPharmacy(Long pharmacyID);

    void creatNewPriceList(PriceListDTO dto) throws Exception;

    void savePriceList(PriceList priceList, PriceListDTO priceListDTO) throws Exception;

    void modifyPriceList(Long id, PriceListDTO priceListDTO) throws Exception;



}
