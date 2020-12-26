package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.ConsultationDTO;
import isa.project.pharmacyapp.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/consultation")
@CrossOrigin
public class ConsultationController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private ConsultationService consultationService;

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
}
