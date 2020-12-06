package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.PharmacistDTO;
import isa.project.pharmacyapp.model.Pharmacist;
import isa.project.pharmacyapp.repository.PharmacistRepository;
import isa.project.pharmacyapp.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    @Autowired
    private PharmacistRepository pharmacistRepository;

    final private static String EXCEPTION_TEXT = "PharmacistServiceImpl::";
    final private static String DOES_NOT_EXISTS = " pharmacist with given id does not exists";
    final private static String UNSUCCESSFUL_EXECUTION = " did not execute successfully";

    @Override
    public PharmacistDTO findById(Long id) {
        Pharmacist pharmacist = pharmacistRepository.findById(id).orElse(null);

        if(pharmacist == null)
            throw new NoSuchElementException(EXCEPTION_TEXT + "findById" + DOES_NOT_EXISTS);

        PharmacistDTO dto = PharmacistDTO.pharmacist2Dto(pharmacist);

        return dto;
    }

    @Override
    public List<PharmacistDTO> findAllByPharmacy(Long pharmacyId) {
        List<Pharmacist> pharmacistList = pharmacistRepository.findAllByPharmacy(pharmacyId);

        ArrayList<PharmacistDTO> pharmacistDTOS = new ArrayList<>();
        for(Pharmacist pharmacist : pharmacistList){
            pharmacistDTOS.add(this.findById(pharmacist.getId()));
        }


        return pharmacistDTOS;
    }

    @Override
    public void createNewPharmacist(PharmacistDTO dto) throws Exception {

    }

    @Override
    public void modifyPharmacist(Long id, PharmacistDTO dto) throws Exception {

    }

    @Override
    public void deletePharmacistById(Long id) throws Exception {

    }
}
