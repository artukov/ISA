package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.PromotionsDTO;
import isa.project.pharmacyapp.model.Promotions;

public interface PromotionsService {
    void createNewPromotion(PromotionsDTO dto) throws Exception;

    void savePromotions(Promotions promotions, PromotionsDTO promotionsDTO) throws Exception;


    void addSubscriber(Long userID, Long pharmacyID) throws Exception;
}
