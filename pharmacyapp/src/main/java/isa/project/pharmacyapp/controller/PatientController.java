package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.ExaminationDTO;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.service.DrugService;
import isa.project.pharmacyapp.service.ExaminationService;
import isa.project.pharmacyapp.service.PharmacistService;
import isa.project.pharmacyapp.service.UserService;
import isa.project.pharmacyapp.user_factory.UserServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient")
public class PatientController {
    private static final String AUTHORITY = "hasAuthority('USER')";

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceFactory serviceFactory;

    @Autowired
    private ExaminationService examinationService;

    @GetMapping(value = "/examinations", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDermaAppointments(Principal user){
        User current = userService.findByEmail(user.getName());
        List<ExaminationDTO> dtos = examinationService.getPatientsDermaAppointments(current.getId());

        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }
}