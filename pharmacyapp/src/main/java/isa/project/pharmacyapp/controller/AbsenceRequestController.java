package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.AbsenceRequestDTO;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.service.AbsenceRequestService;
import isa.project.pharmacyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/absence")
public class AbsenceRequestController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private AbsenceRequestService absenceRequestService;


    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> createNewAbsenceRequest(@RequestBody AbsenceRequestDTO absenceRequestDTO, Principal user){

        User current = userService.findByEmail(user.getName());

        if(current.getRole() == UserRoles.PATIENT){
            return new ResponseEntity<>("Not allowed", HttpStatus.UNAUTHORIZED);
        }

        absenceRequestDTO.setUserId(current.getId());
        /**
         * TODO
         * Create absenceRequest context on front
         * */

        try {
            absenceRequestService.createNewAbsenceRequest(absenceRequestDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/allRequests/{pharmacyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllRequestFromPharmacy(@PathVariable("pharmacyID")Long pharmacyID){
        List<AbsenceRequestDTO> absenceRequestDTOS = this.absenceRequestService.findAllPharmacyPharmacistUnanswered(pharmacyID);
        return new ResponseEntity<>(absenceRequestDTOS, HttpStatus.OK);
    }
    
    @PutMapping(value = "/answerRequest/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> answerToTheRequest(@RequestBody AbsenceRequestDTO requestDTO){
        try {
            absenceRequestService.modifyWithStatus(requestDTO.getId(), requestDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
