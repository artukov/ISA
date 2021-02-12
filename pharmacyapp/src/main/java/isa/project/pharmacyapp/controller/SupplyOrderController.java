package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.DrugDTO;
import isa.project.pharmacyapp.dto.OrderSupplierDTO;
import isa.project.pharmacyapp.dto.SupplyOrderDTO;
import isa.project.pharmacyapp.model.OrderStatus;
import isa.project.pharmacyapp.model.Supplier;
import isa.project.pharmacyapp.model.SupplyOrder;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.model.many2many.SupplierOrder;
import isa.project.pharmacyapp.model.many2many.SupplyOrderDrug;
import isa.project.pharmacyapp.service.DrugService;
import isa.project.pharmacyapp.service.EmailService;
import isa.project.pharmacyapp.service.SupplierService;
import isa.project.pharmacyapp.service.SupplyOrderService;
import isa.project.pharmacyapp.user_factory.UserServiceFactory;
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

    @Autowired
    private UserServiceFactory serviceFactory;
    
    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/statuses", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getOrderStatuses(){
        return new ResponseEntity<>(OrderStatus.values(),HttpStatus.OK);
    }

    @GetMapping(value = "/findWithStatus/{status}/{pharmacyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllPharmacyOrders(@PathVariable("status") OrderStatus status,
                                                  @PathVariable("pharmacyID") Long pharmacyID){

        List<SupplyOrderDTO> orderDTOS = supplyOrderService.findPharmacyOrders(pharmacyID, status);

        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);

    }

    @GetMapping(value ="/findWithoutOffers/{pharmacyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllWithoutOffers(@PathVariable("pharmacyID") Long pharmacyID){

        List<SupplyOrderDTO> orderDTOS = supplyOrderService.findWithoutOffer(pharmacyID);

        return new ResponseEntity<>(orderDTOS,HttpStatus.OK);
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

    @PutMapping(value="/modify/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> modifyOrderWithoutOffer(@RequestBody SupplyOrderDTO supplyOrderDTO){

        try {
            supplyOrderService.modifySupplyOrder(supplyOrderDTO.getId(),supplyOrderDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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

        SupplierService supplierService = (SupplierService) serviceFactory.getUserService(UserRoles.SUPPLIER);
        try {
            supplierService.updateWareHouse(order.getDrugs(),dto.getSupplierID());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        StringBuilder builder = new StringBuilder();
        for(SupplierOrder supplier : order.getSuppliers()){
            Supplier user = supplier.getId().getSupplier();
            builder.append("Dear, ");
            builder.append(user.getFirstname());
            builder.append(" ");
            builder.append(user.getLastname());
            builder.append("\n");
            if(user.getId().equals(dto.getSupplierID())){
                builder.append("Yours offer has been accepted\n");
                emailService.sendSimpleMessage(user.getEmail(),"Offered accepted",builder.toString());
                builder.delete(0,builder.length());
                continue;
            }
            builder.append("Yours offer has been denied\n");
            builder.append("We would like to continue our engagement in further business");

            emailService.sendSimpleMessage(user.getEmail(),"Offered denied",builder.toString());
            builder.delete(0,builder.length());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> deleteOrderWithoutAnyOffers(@PathVariable("id") Long id){

        try {
            supplyOrderService.deleteSupplyOrder(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);


    }


}
