package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.DermatologistDTO;
import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.model.Dermatologist;
import isa.project.pharmacyapp.model.DermatologistRatings;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.repository.DermatologistRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.service.DermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DermatologistServiceImpl implements DermatologistService {

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;


    final private static String EXCEPTION_TEXT = "DermatologistServiceImpl::";
    final private static String DOES_NOT_EXISTS = " dermatologist with given id does not exists";
    final private static String UNSUCCESSFUL_EXECUTION = " did not execute successfully";

    @Override
    public DermatologistDTO findById(Long id) {
        Dermatologist dermatologist = this.dermatologistRepository.findById(id).orElse(null);

        if(dermatologist == null)
            throw new NoSuchElementException(EXCEPTION_TEXT + "findById" + DOES_NOT_EXISTS);

        return DermatologistDTO.dermatologist2Dto(dermatologist);

    }

    @Override
    public List<DermatologistDTO> findAllByPharmacy(Long pharmacyId) {

        List<Dermatologist> dermatologistList = this.dermatologistRepository.findAllByPharmacy(pharmacyId);

        ArrayList<DermatologistDTO> dermatologistDTOs = new ArrayList<>();

        for(Dermatologist dermatologist : dermatologistList){
            dermatologistDTOs.add(DermatologistDTO.dermatologist2Dto(dermatologist));
        }

        return dermatologistDTOs;
    }

    @Override
    public void createNewDermatologist(DermatologistDTO dermatologistDTO) throws Exception {

    }

    @Override
    public void modifyDermatologist(Long id, DermatologistDTO dermatologistDTO) throws Exception {

    }

    @Override
    public void deleteDermatologist(Long id) throws Exception {

    }

    @Override
    public Dermatologist saveDermatologist(Dermatologist dermatologist, DermatologistDTO dermatologistDTO) {
        return null;
    }

    @Override
    public Double getAvgRatings(Long dermaID, Long pharmacyID) throws Exception {

        if(( dermatologistRepository.findById(dermaID) == null) || (pharmacyRepository.findById(pharmacyID).orElse(null) == null ))
            throw new Exception(getClass().getName()+"::getAvgRatings not exists");

        return dermatologistRepository.getAvgRatings(dermaID, pharmacyID);

    }

    @Override
    public User findByEmail(String email) {
        return this.dermatologistRepository.findByEmail(email);
    }

    @Override
    public User saveNewUser(UserDTO userDTO) throws Exception {
        return this.saveDermatologist(new Dermatologist(), (DermatologistDTO) userDTO);
    }
}
