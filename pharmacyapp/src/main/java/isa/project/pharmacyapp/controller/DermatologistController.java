package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.DermatologistDTO;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.service.DermatologistService;
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
@RequestMapping(value = "/dermatologist")
public class DermatologistController {

    private static final String AUTHORITY = "hasAuthority('USER')";

//    @Autowired
//    private DermatologistService dermatologistService;
    @Autowired
    private UserServiceFactory serviceFactory;

    @GetMapping(value = "/findByPharmacy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDermatologistInPharmacy(@PathVariable("id")Long id){

        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);

        List<DermatologistDTO> dermatologistDTOList = dermatologistService.findAllByPharmacy(id);

        return new ResponseEntity<>(dermatologistDTOList, HttpStatus.OK);

    }

    @DeleteMapping(value = "/delete/pharmacy/{dermaID}/{pharmacyID}")
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> deleteDermatologistFromPharmacy(@PathVariable("dermaID") Long dermaID,
                                                             @PathVariable("pharmacyID") Long pharmacyID){

        DermatologistService service = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);

        try {
            service.deleteDermatologistPharmacy(dermaID, pharmacyID);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
