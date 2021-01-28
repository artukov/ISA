package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.ExaminationDTO;
import isa.project.pharmacyapp.exception.DermatologistNotWorkingException;
import isa.project.pharmacyapp.exception.ExaminationOverlappingException;
import isa.project.pharmacyapp.model.Examination;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.service.CalendarService;
import isa.project.pharmacyapp.service.DermatologistService;
import isa.project.pharmacyapp.service.ExaminationService;
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
@RequestMapping(value = "/examination")
@CrossOrigin
public class ExaminationController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private ExaminationService examinationService;



    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> newExamination(@RequestBody ExaminationDTO dto){
        try {
             this.examinationService.createNewExamination(dto);
        }
        catch (ExaminationOverlappingException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(this.getClass().getName()+"::newExamination Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping(value = "/new/{pharmacyID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> newPharmacyExamination(@RequestBody ExaminationDTO dto, @PathVariable("pharmacyID")Long pharmacyID){

        try {
            examinationService.createNewExaminationPharmacy(dto, pharmacyID);
        }
        catch (DermatologistNotWorkingException dnwe){
            return new ResponseEntity<>(dnwe.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/modify/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> modifyExamination(@RequestBody ExaminationDTO modifyDTO, @PathVariable("id") Long id, Principal user){

        User current = userService.findByEmail(user.getName());

        modifyDTO.setPatient_id(current.getId());

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

    @GetMapping(value = "/freePharmacyExam/{pharmacyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacyExaminations(@PathVariable("pharmacyID")Long pharmacyID){

        List<ExaminationDTO> examinationDTOList = this.examinationService.findFreeExaminations(pharmacyID);

        return new ResponseEntity<>(examinationDTOList, HttpStatus.OK);
    }

}
