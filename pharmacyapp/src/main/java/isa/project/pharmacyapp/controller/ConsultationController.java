package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.ConsultationDTO;
import isa.project.pharmacyapp.dto.DermatologistDTO;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.service.CalendarService;
import isa.project.pharmacyapp.service.ConsultationService;
import isa.project.pharmacyapp.service.DermatologistService;
import isa.project.pharmacyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/consultation")
@CrossOrigin
public class ConsultationController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private CalendarService calendarService;

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> createNewConsultation(@RequestBody ConsultationDTO dto){

        try {
            consultationService.createNewConsultation(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> modifyConsultation(@RequestBody ConsultationDTO consultationDTO){

        try {
            consultationService.modifyConsultation(consultationDTO);
        }
        catch (NoSuchElementException ele){
            ele.printStackTrace();
            return new ResponseEntity<>("ConsultationController::modifyConsultation " +
                    "Consultation with given id does not exists",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
