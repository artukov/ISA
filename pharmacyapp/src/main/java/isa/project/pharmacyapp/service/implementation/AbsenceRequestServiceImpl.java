package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.AbsenceRequestDTO;
import isa.project.pharmacyapp.dto.ReservationDTO;
import isa.project.pharmacyapp.model.*;
import isa.project.pharmacyapp.repository.AbsenceRequestRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.repository.UserRepository;
import isa.project.pharmacyapp.service.AbsenceRequestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class AbsenceRequestServiceImpl implements AbsenceRequestService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AbsenceRequestRepository absenceRequestRepository;



    @Override
    public void createNewAbsenceRequest(AbsenceRequestDTO absenceRequestDTO) throws Exception {
        AbsenceRequest absenceRequest = new AbsenceRequest();

        try {
            this.saveAbsenceRequest(absenceRequest, absenceRequestDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void saveAbsenceRequest(AbsenceRequest absenceRequest, AbsenceRequestDTO absenceRequestDTO) throws Exception {

        absenceRequest.setId(absenceRequestDTO.getId());
        absenceRequest.setDescription(absenceRequestDTO.getDescription());
        absenceRequest.setEndDate(absenceRequestDTO.getEndDate());
        absenceRequest.setStartDate(absenceRequestDTO.getStartDate());
        absenceRequest.setStatus(absenceRequestDTO.getStatus());

        Pharmacy pharmacy = pharmacyRepository.findById(absenceRequestDTO.getPharmacyId()).orElse(null);
        if(pharmacy == null){
            throw  new Exception("Pharmacy does not exists");
        }
        User user = userRepository.findById(absenceRequestDTO.getUserId()).orElse(null);
        if(user == null){
            throw  new Exception("User does not exists");
        }

        absenceRequest.setPharmacy(pharmacy);
        absenceRequest.setUser(user);

        try{
            absenceRequestRepository.save(absenceRequest);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("Saving reservation");
        }


    }
}
