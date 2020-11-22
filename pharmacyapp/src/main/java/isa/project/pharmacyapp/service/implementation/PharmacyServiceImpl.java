package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.PharmacyDTO;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Override
    public PharmacyDTO findById(Long id) {
        Pharmacy pharmacy = pharmacyRepository.findById(id).orElse(null);

        if(pharmacy == null){
            throw new NoSuchElementException("PharmacyServiceImpl::findById pharmacy does not exists for given id");
        }

        PharmacyDTO pharmacyDTO = PharmacyDTO.pharmacy2DTO(pharmacy);

        return pharmacyDTO;
    }

    @Override
    public List<PharmacyDTO> findAll() {
        return null;
    }

    @Override
    public void createNewPharmacy(PharmacyDTO pharmacyDTO) {

    }

    @Override
    public void modifyPharmacy(Long id, PharmacyDTO pharmacyDTO) {

    }

    @Override
    public void deletePharmacy(Long id) {

    }
}
