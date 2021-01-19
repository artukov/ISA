package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.ReservationDTO;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.service.ReservationService;
import isa.project.pharmacyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/reservation")
@CrossOrigin
public class ReservationController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private ReservationService reservationService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @PostMapping(value = "/new/{pharmacyID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> createNewDrugReservation(@RequestBody ReservationDTO reservationDTO,
                                                      @PathVariable("pharmacyID")Long pharmacyID,Principal user){

        User current = userService.findByEmail(user.getName());

        if(current.getRole() != UserRoles.PATIENT){
            return new ResponseEntity<>("Not allowed",HttpStatus.UNAUTHORIZED);
        }

        reservationDTO.setPatientID(current.getId());
        reservationDTO.setPharmacyID(pharmacyID);
        /**
         * TODO
         * Create pharmacy context on front
         * */

        try {
            reservationService.createNewReservation(reservationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(value = "/accepted/{id}")
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> drugAccepted(@PathVariable("id")Long id){

        try {
            reservationService.drugAccepted(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
