package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.DrugDTO;
import isa.project.pharmacyapp.model.TimeSpam;
import isa.project.pharmacyapp.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/drug")
public class DrugController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private DrugService drugService;

    @GetMapping(value = "/avgConsumption/{timespam}/{pharmacyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> getAverageDrugConsumption(@PathVariable("timespam")TimeSpam timeSpam,
                                                      @PathVariable("pharmacyID") Long pharmacyID){

        List<Double> consumption = drugService.getConsumptionStatistics(timeSpam, pharmacyID);

        return new ResponseEntity<>(consumption, HttpStatus.OK);
    }

    @GetMapping(value = "/allPharmacyDrugs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacyAllDrugs(@PathVariable("id") Long pharmacyID){

        List<DrugDTO> drugDTOS = this.drugService.findAllPharmacyDrugs(pharmacyID);

        return new ResponseEntity<>(drugDTOS,HttpStatus.OK);

    }



}
