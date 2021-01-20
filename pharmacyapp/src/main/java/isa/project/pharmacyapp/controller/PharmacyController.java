package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.DateLimitsDTO;
import isa.project.pharmacyapp.dto.PharmacyDTO;
import isa.project.pharmacyapp.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/pharmacy")
@CrossOrigin
public class PharmacyController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> findPharmacyById(@PathVariable("id") Long id){
        PharmacyDTO dto = null;
       try{
           dto = pharmacyService.findById(id);
       }catch (NoSuchElementException ele){
           return new ResponseEntity<>("PharmacyController::findPharmacyById there is no pharmacy by given id",
                   HttpStatus.BAD_REQUEST);
       }

       if(dto == null)
           return new ResponseEntity<>("PharmacyController::findPharmacyById Server error", HttpStatus.NO_CONTENT);//204

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> findAllPharmacies(){

        ArrayList<PharmacyDTO> pharmacyDTOS = (ArrayList<PharmacyDTO>) pharmacyService.findAll();

        if(pharmacyDTOS == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204

        return new ResponseEntity<>(pharmacyDTOS,HttpStatus.OK);

    }

    @PostMapping(value = "/new",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> createNewPharmacy(@RequestBody PharmacyDTO dto){

        try {
            pharmacyService.createNewPharmacy(dto);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("PharmacyController::createNewPharmacy Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);


    }

    @PutMapping(value = "/modify/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> modifyPharmacy(@PathVariable("id") Long id, @RequestBody PharmacyDTO dto) {


        PharmacyDTO retDTO;
        try {
            retDTO = pharmacyService.modifyPharmacy(id, dto);
        } catch (NoSuchElementException ele) {
            ele.printStackTrace();
            return new ResponseEntity<>("PharmacyController::modifyPharmacy there is no pharmacy by a given Id", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("PharmacyController::modifyPharmacy Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(retDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/id")
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> deletePharmacy(@PathVariable("id") Long id){

        try {
            pharmacyService.deletePharmacy(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("PharmacyController::deletePharmacy Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/finances/{pharmacyID}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacyFinances(@RequestBody DateLimitsDTO limitsDTO , @PathVariable("pharmacyID") Long pharmacyID){

        Double priceSum = this.pharmacyService.calculateFinances(limitsDTO, pharmacyID);

        if(priceSum == null){
            return new ResponseEntity<>(0,HttpStatus.OK);
        }

        return new ResponseEntity<>(priceSum, HttpStatus.OK);

    }

    @GetMapping(value = "/address/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacyAddress(@PathVariable("id") Long id){
       return  new ResponseEntity<>( pharmacyService.getAddress(id), HttpStatus.OK);

    }







}
