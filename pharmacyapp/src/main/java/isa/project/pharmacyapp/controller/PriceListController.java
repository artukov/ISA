package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.PriceListDTO;
import isa.project.pharmacyapp.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pricelist")
@CrossOrigin
public class PriceListController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private PriceListService priceListService;


    @GetMapping(value = "/findLatestPriceList/{pharmacyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getLatestPriceList(@PathVariable("pharmacyID") Long pharmacyID){

        PriceListDTO priceListDTO = priceListService.findLatestPharmacyPriceList(pharmacyID);

        return new ResponseEntity<>(priceListDTO,HttpStatus.OK);
    }

    @GetMapping(value = "/findPriceList/{pharmacyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacyPriceLists(@PathVariable("pharmacyID") Long pharmacyID){

        List<PriceListDTO> dtos = priceListService.findAllPharmacy(pharmacyID);

        return new ResponseEntity<>(dtos,HttpStatus.OK);

    }


    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> postNewPriceList(@RequestBody PriceListDTO dto){

        try {
            priceListService.creatNewPriceList(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(value = "/modify/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> putModifiedPriceList(@RequestBody PriceListDTO priceListDTO, @PathVariable("id")Long id){

        try {
            priceListService.modifyPriceList( id, priceListDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }




}
