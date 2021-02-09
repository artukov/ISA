package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.AbsenceRequestDTO;
import isa.project.pharmacyapp.dto.ReservationDTO;
import isa.project.pharmacyapp.model.*;
import isa.project.pharmacyapp.repository.AbsenceRequestRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.repository.UserRepository;
import isa.project.pharmacyapp.service.AbsenceRequestService;
import isa.project.pharmacyapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AbsenceRequestServiceImpl implements AbsenceRequestService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AbsenceRequestRepository absenceRequestRepository;

    @Autowired
    private EmailService emailService;



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

        AbsenceRequestDTO.dto2request(absenceRequest,absenceRequestDTO);


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
            throw new Exception("Saving absenceRequest");
        }

        sendNotification(absenceRequest);



    }

    private void sendNotification(AbsenceRequest absenceRequest) {
        StringBuilder builder = new StringBuilder();
        builder.append("Dear, ");
        builder.append(absenceRequest.getUser().getFirstname());
        builder.append(' ');
        builder.append(absenceRequest.getUser().getLastname());
        builder.append("\n");
        if(absenceRequest.getStatus()){
            builder.append("Yours request for holiday has been accepted \n");
        }
        else{
            builder.append("Yours request for holiday has been denied \n");
            builder.append("The reason was \n");
            builder.append(absenceRequest.getDescription());
            builder.append("\n");
        }
        builder.append("Holiday starts on ");
        builder.append(absenceRequest.getStartDate());
        builder.append("\n");
        builder.append("Holiday end on ");
        builder.append(absenceRequest.getEndDate());
        builder.append("\n");

        emailService.sendSimpleMessage(absenceRequest.getUser().getEmail(),"Request for a holiday at pharmacy "
                + absenceRequest.getPharmacy().getName(), builder.toString());

    }

    @Override
    public void modifyWithStatus(Long id, AbsenceRequestDTO requestDTO) throws Exception {
        AbsenceRequest request = absenceRequestRepository.findById(id).orElse(null);
        if(request == null){
            throw new Exception("Not valid id for absence request");
        }

        this.saveAbsenceRequest(request,requestDTO);

    }

    @Override
    public List<AbsenceRequestDTO> findAllDermatologistsAbsenceRequests() {
        List<AbsenceRequest> absenceRequests = absenceRequestRepository.findAllDermatologistsRequests();

        ArrayList<AbsenceRequestDTO> requestDTOS = new ArrayList<>();
        for(AbsenceRequest absenceRequest : absenceRequests){
            requestDTOS.add(AbsenceRequestDTO.request2DTO(absenceRequest));
        }

        return requestDTOS;
    }

    @Override
    public List<AbsenceRequestDTO> findAllPharmacyPharmacistUnanswered(Long pharmacyID) {
        List<AbsenceRequest> requests = absenceRequestRepository.findAllPharmacyPharmacistUnanswered(pharmacyID);

        ArrayList<AbsenceRequestDTO> requestDTOS = new ArrayList<>();
        for(AbsenceRequest absenceRequest : requests){
            requestDTOS.add(AbsenceRequestDTO.request2DTO(absenceRequest));
        }

        return requestDTOS;
    }
}
