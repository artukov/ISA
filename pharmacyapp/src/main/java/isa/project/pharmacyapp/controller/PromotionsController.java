package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.PromotionsDTO;
import isa.project.pharmacyapp.service.PromotionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/promotions")
@CrossOrigin
public class PromotionsController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private PromotionsService promotionsService;


    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> postNewPromotion(@RequestBody PromotionsDTO dto){

        try {
            promotionsService.createNewPromotion(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping(value="/subscribe", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> subscribeToPharmacyPromotion(@RequestBody Subscriber subscriber){

        try {
            promotionsService.addSubscriber(subscriber.getUserID(),subscriber.getPharmacyID());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    static public class Subscriber {
        private Long userID;
        private Long pharmacyID;

        public Subscriber() {
        }

        public Long getUserID() {
            return userID;
        }

        public void setUserID(Long userID) {
            this.userID = userID;
        }

        public Long getPharmacyID() {
            return pharmacyID;
        }

        public void setPharmacyID(Long pharmacyID) {
            this.pharmacyID = pharmacyID;
        }
    }
}
