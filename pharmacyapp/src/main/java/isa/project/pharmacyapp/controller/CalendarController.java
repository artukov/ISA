package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/calendar" )
@CrossOrigin
public class CalendarController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private CalendarService service;


    @GetMapping(value="/allTheYears/{pharmacyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacyCalendarYears(@PathVariable("pharmacyID") Long pharmacyID){

        List<Double> years = service.getPharmacyCalendarYears(pharmacyID);

        return new ResponseEntity<>(years, HttpStatus.OK);

    }

}
