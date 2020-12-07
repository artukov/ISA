package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.service.DermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/dermatologist")
public class DermatologistController {

    @Autowired
    private DermatologistService dermatologistService;
}
