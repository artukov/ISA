package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.PromotionsDTO;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.model.Promotions;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.repository.PromotionsRepository;
import isa.project.pharmacyapp.repository.UserRepository;
import isa.project.pharmacyapp.service.EmailService;
import isa.project.pharmacyapp.service.PromotionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PromotionsServiceImpl implements PromotionsService {

    @Autowired
    private PromotionsRepository repository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void createNewPromotion(PromotionsDTO dto) throws Exception {
        Promotions promotions = new Promotions();
        savePromotions(promotions,dto);

    }

    @Override
    public void addSubscriber(Long userID, Long pharmacyID) throws Exception {
        User user = userRepository.findById(userID).orElse(null);
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyID).orElseThrow(() -> new Exception("Pharmacy with give id does note exists"));

        if(pharmacy.getSubscribers() == null)
            pharmacy.setSubscribers(new ArrayList<>());
        pharmacy.getSubscribers().add(user);

        try{
            pharmacyRepository.save(pharmacy);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("Saving pharmacy with added subscribers");
        }
    }

    @Override
    public void savePromotions(Promotions promotions, PromotionsDTO promotionsDTO) throws Exception {

        PromotionsDTO.dto2promotions(promotions, promotionsDTO);
        Pharmacy pharmacy = pharmacyRepository.findById(promotionsDTO.getPharmacyID()).orElseThrow(() -> new Exception("Pharmacy with given id does not exists"));
        promotions.setPharmacy(pharmacy);
        try{
            repository.save(promotions);
        }
        catch (Exception e){
            e.printStackTrace();
            throw  new Exception("Saving promotions");
        }

        for(User user : pharmacy.getSubscribers()){
            sendEmailToUser(user,pharmacy.getName(), promotions);
        }




    }

    private void sendEmailToUser(User user, String name, Promotions promotions) {

        StringBuilder builder = new StringBuilder();
        builder.append("Dear ");
        builder.append(user.getFirstname());
        builder.append(" ");
        builder.append(user.getLastname());
        builder.append("\n");
        builder.append("We would like to alert You that we have a new promotion.\n");
        builder.append(promotions.getContent());
        builder.append("\n");
        builder.append("Promotion starts at ");
        builder.append(promotions.getStartDate());
        builder.append("\n");
        builder.append("Promotion ends at ");
        builder.append(promotions.getEndDate());

        emailService.sendSimpleMessage(user.getEmail(),"New Pharmacy "+ name+" promotion",builder.toString());
    }
}
