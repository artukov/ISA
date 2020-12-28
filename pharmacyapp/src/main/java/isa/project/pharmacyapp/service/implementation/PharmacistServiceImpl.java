package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.PharmacistDTO;
import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.model.*;
import isa.project.pharmacyapp.repository.PharmacistRepository;
import isa.project.pharmacyapp.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            pharmacistDTOS.add(PharmacistDTO.pharmacist2Dto(pharmacist));
        }


        return pharmacistDTOS;
    }

    @Override
    public void createNewPharmacist(PharmacistDTO dto) throws Exception {

    }

    @Override
    public void modifyPharmacist(Long id, PharmacistDTO dto) throws Exception {

    }

    @Transactional
    @Override
    public void deletePharmacistById(Long id) throws Exception {
        Pharmacist pharmacist = pharmacistRepository.findById(id).orElse(null);
        if(pharmacist == null){
            throw new Exception("There is no pharmacist with given id");
        }

        if(pharmacistRepository.existsUnfinishedConsultation(id) != 0.0){
            throw new Exception("Pharmacist has a consultation");
        }

        pharmacistRepository.deletePharmacistFromPharmacy(id);

    }

    @Override
    public Pharmacist savePharmacist(Pharmacist pharmacist, PharmacistDTO pharmacistDTO) {
        /**
         * TODO
         * Saving pharmacist
         * */
        return null;
    }

    @Override
    public Double getAvgRating(Long id) throws Exception {

        if(pharmacistRepository.findById(id).orElse(null) == null){
            throw new Exception(getClass().getName()+"::getAvgRating pharmacist does not exists");
        }


        return pharmacistRepository.getAvgRatings(id);
    }

    /**************************************
     * UserService methods
     *
     * *************************************/

    @Override
    public User findByEmail(String email) {
        return this.pharmacistRepository.findByEmail(email);
    }

    @Override
    public User saveNewUser(UserDTO userDTO) {
        return this.savePharmacist(new Pharmacist(), (PharmacistDTO) userDTO);
    }


}
