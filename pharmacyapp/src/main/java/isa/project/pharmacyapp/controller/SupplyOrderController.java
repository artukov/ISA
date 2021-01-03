package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.DrugDTO;
import isa.project.pharmacyapp.dto.OrderSupplierDTO;
import isa.project.pharmacyapp.dto.SupplyOrderDTO;
import isa.project.pharmacyapp.model.OrderStatus;
import isa.project.pharmacyapp.model.SupplyOrder;
import isa.project.pharmacyapp.model.many2many.SupplyOrderDrug;
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

    @Autowired
    private DrugService drugService;

    @GetMapping(value = "/findWithStatus/{status}/{pharmacyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllPharmacyOrders(@PathVariable("status") OrderStatus status,
                                                  @PathVariable("pharmacyID") Long pharmacyID){

        List<SupplyOrderDTO> orderDTOS = supplyOrderService.findPharmacyOrders(pharmacyID, status);

        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);

    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> createNewSupplyOrder(@RequestBody SupplyOrderDTO orderDTO) {

        /**
         * TODO
         * Check if drug exists in the pharmacy
         * */

        try {
            this.supplyOrderService.createNewSupplyOrder(orderDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);


    }

    @PutMapping(value = "/acceptOffer", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public  ResponseEntity<?> acceptOfferForOrder(@RequestBody OrderSupplierDTO dto){

        SupplyOrder order = null;

        try {
            order =  supplyOrderService.acceptOfferForOrder(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        for(SupplyOrderDrug drug : order.getDrugs()){
//            DrugDTO drugDTO = drugService.findById(drug.getId().getDrug().getId());
            try {
                drugService.addToPharmacyDrug(drug.getId().getDrug(),
                        order.getPharmacyAdmin().getPharmacy().getId(),drug.getAmount());
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
