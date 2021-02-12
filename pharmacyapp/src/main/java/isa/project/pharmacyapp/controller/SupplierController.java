package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.OrderSupplierDTO;
import isa.project.pharmacyapp.dto.SupplierDTO;
import isa.project.pharmacyapp.dto.SupplyOrderDTO;
import isa.project.pharmacyapp.dto.WareHouseDTO;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.service.SupplierService;
import isa.project.pharmacyapp.service.SupplyOrderService;
import isa.project.pharmacyapp.user_factory.UserServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/supplier")
@CrossOrigin
public class SupplierController {

    private static final String AUTHORITY = "hasAuthority('USER')";
    private static final String ADMIN_AUTHORITY = "hasAuthority('ADMIN')";

    @Autowired
    private SupplyOrderService supplyOrderService;

    @Autowired
    private UserServiceFactory serviceFactory;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping(value="/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(ADMIN_AUTHORITY)
    public ResponseEntity<?> postNewSupplier(@RequestBody SupplierDTO supplierDTO){

        SupplierService supplierService = (SupplierService) serviceFactory.getUserService(UserRoles.SUPPLIER);
        supplierDTO.setPassword(encoder.encode(supplierDTO.getPassword()));
        try {
            supplierService.createNewSupplier(supplierDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value="/modify", consumes=MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> putModifiedSupplier(@RequestBody SupplierDTO supplierDTO){
        SupplierService supplierService = (SupplierService) serviceFactory.getUserService(UserRoles.SUPPLIER);
        try {
            supplierService.modifySupplier(supplierDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/allIncomingOrders/{supplierID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllIncomingOrders(@PathVariable("supplierID") Long supplierID){

        SupplierService supplierService = (SupplierService) serviceFactory.getUserService(UserRoles.SUPPLIER);

        List<SupplyOrderDTO> supplyOrderDTOS = supplierService.findAllIncomingOrders(supplierID);

        return new ResponseEntity<>(supplyOrderDTOS,HttpStatus.OK);

    }

    @GetMapping(value = "/allOrders/{supplierID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllSupplierOrders(@PathVariable("supplierID") Long supplierID){
        SupplierService supplierService = (SupplierService) serviceFactory.getUserService(UserRoles.SUPPLIER);
        List<SupplyOrderDTO> supplierDTOS = supplierService.findAllSupplierOrders(supplierID);
        return new ResponseEntity<>(supplierDTOS,HttpStatus.OK);
    }


    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllSystemSuppliers(){
        SupplierService supplierService = (SupplierService) serviceFactory.getUserService(UserRoles.SUPPLIER);

        List<SupplierDTO> supplierDTOList = supplierService.findAll();

        return new ResponseEntity<>(supplierDTOList, HttpStatus.OK);

    }

    @PutMapping(value = "/offer", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> makeAnOfferForOrder(@RequestBody OrderSupplierDTO dto){


        try {
            supplyOrderService.modifyOrderWithOffer(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/warehouse/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getWarehouse(@PathVariable("id") Long supplierID){
        SupplierService supplierService = (SupplierService) serviceFactory.getUserService(UserRoles.SUPPLIER);
        WareHouseDTO wareHouseDTO = supplierService.getWarehouse(supplierID);

        return new ResponseEntity<>(wareHouseDTO, HttpStatus.OK);

    }
}
