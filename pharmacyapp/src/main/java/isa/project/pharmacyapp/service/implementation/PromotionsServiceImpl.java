package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.PromotionsDTO;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.model.Promotions;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.repository.PromotionsRepository;
import isa.project.pharmacyapp.repository.UserRepository;
import isa.project.pharmacyapp.service.PromotionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
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

        /**
         * TODO
         * Sending email when the promotion is created
         * */


    }
}
