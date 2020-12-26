package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.PharmacistDTO;
import isa.project.pharmacyapp.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/pharmacist")
public class PharmacistController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private PharmacistService pharmacistService;

    @GetMapping(value = "/findAllByPharmacy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllPharmacyPharmacists(@PathVariable("id") Long id){

        List<PharmacistDTO> pharmacistDTOList = pharmacistService.findAllByPharmacy(id);

        return new ResponseEntity<>(pharmacistDTOList, HttpStatus.OK);

    }


}
