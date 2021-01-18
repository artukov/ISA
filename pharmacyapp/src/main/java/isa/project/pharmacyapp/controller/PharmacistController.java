package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.PatientDTO;
import isa.project.pharmacyapp.dto.PharmacistDTO;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.service.DermatologistService;
import isa.project.pharmacyapp.service.PharmacistService;
import isa.project.pharmacyapp.user_factory.UserServiceFactory;
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
    private UserServiceFactory serviceFactory;

    @GetMapping(value = "/findAllByPharmacy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllPharmacyPharmacists(@PathVariable("id") Long id){

        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);

        List<PharmacistDTO> pharmacistDTOList = pharmacistService.findAllByPharmacy(id);

        return new ResponseEntity<>(pharmacistDTOList, HttpStatus.OK);

    }

    @DeleteMapping(value = "/delete/pharmacy/{id}")
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> deletePharmacistFromPharmacy(@PathVariable("id")Long id){

        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);

        try {
            pharmacistService.deletePharmacistById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findPatients/{pharmaId}/{orderCondition}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> findAllPatients(@PathVariable("pharmaId") Long pharmaId, @PathVariable("orderCondition") String orderCondition ){
        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);

        List<PatientDTO> patientDTOList = pharmacistService.findAllPatients(pharmaId, orderCondition);

        return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
    }


}
