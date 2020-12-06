package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/pharmacist")
public class PharmacistController {

    @Autowired
    private PharmacistService pharmacistService;

}
