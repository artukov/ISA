package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.ExaminationDTO;
import isa.project.pharmacyapp.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/examination")
@CrossOrigin
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> newExamination(@RequestBody ExaminationDTO dto){

        try {
            this.examinationService.createNewExamination(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);


    }





}
