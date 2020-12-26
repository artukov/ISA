package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.ExaminationDTO;
import isa.project.pharmacyapp.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/examination")
@CrossOrigin
public class ExaminationController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private ExaminationService examinationService;

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> newExamination(@RequestBody ExaminationDTO dto){

        try {
            this.examinationService.createNewExamination(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(this.getClass().getName()+"::newExamination Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(value = "/modify/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> modifyExamination(@RequestBody ExaminationDTO modifyDTO, @PathVariable("id") Long id){

        try {
            examinationService.modifyExamination(id, modifyDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(this.getClass().getName()+"::modifyExamination Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/freeDermaExam/{dermaID}/{pharmacyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDermatologistFreeExaminations(@PathVariable("dermaID") Long dermaID,
                                                              @PathVariable("pharmacyID") Long pharmacyID){

        List<ExaminationDTO> examinationDTOList = this.examinationService.findFreeExaminations(dermaID, pharmacyID);

        return new ResponseEntity<>(examinationDTOList, HttpStatus.OK);

    }





}
