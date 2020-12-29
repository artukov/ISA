package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.DrugDTO;
import isa.project.pharmacyapp.dto.SupplyOrderDTO;
import isa.project.pharmacyapp.service.DrugService;
import isa.project.pharmacyapp.service.SupplyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/supplyorder")
@CrossOrigin
public class SupplyOrderController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private SupplyOrderService supplyOrderService;

    @Autowired
    private DrugService drugService;

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> createNewSupplyOrder(@RequestBody SupplyOrderDTO orderDTO) {

//        for(Long drugID : orderDTO.getDrugs()){
//            if(drugService.drugExistsInPharmacy(drugID, orderDTO.getPharmacyID()));
//        }

        try {
            this.supplyOrderService.createNewSupplyOrder(orderDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);


    }

}
