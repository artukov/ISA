package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.DrugDTO;
import isa.project.pharmacyapp.dto.SupplyOrderDTO;
import isa.project.pharmacyapp.model.OrderStatus;
import isa.project.pharmacyapp.service.DrugService;
import isa.project.pharmacyapp.service.SupplyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/supplyorder")
@CrossOrigin
public class SupplyOrderController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private SupplyOrderService supplyOrderService;

//    @Autowired
//    private DrugService drugService;

    @GetMapping(value = "/findStatus/{status}/{pharmacyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllPharmacyOrders(@PathVariable("status") OrderStatus status,
                                                  @PathVariable("pharmacyID") Long pharmacyID){

        List<SupplyOrderDTO> orderDTOS = supplyOrderService.findPharmacyOrders(pharmacyID, status);

        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);

    }

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

    @PostMapping(value = "/new/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public  ResponseEntity<?> createListOfSupplyOrders(@RequestBody List<SupplyOrderDTO> orderDTOS){

        for(SupplyOrderDTO orderDTO : orderDTOS){
            try {
                supplyOrderService.createNewSupplyOrder(orderDTO);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping(value = "/makeAnOffer", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> makeAnOfferForSupply(@RequestBody SupplyOrderDTO orderDTO){

        try {
            supplyOrderService.modifySupplyOrderOffer(orderDTO.getId(), orderDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
