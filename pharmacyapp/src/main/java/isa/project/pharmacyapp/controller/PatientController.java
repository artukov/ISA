package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.*;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.service.*;
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

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private ERecepitService eRecepitService;

    @Autowired
    private DrugService drugService;

    @GetMapping(value = "/examinations", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDermaAppointments(Principal user){
        User current = userService.findByEmail(user.getName());
        List<ExaminationDTO> dtos = examinationService.getPatientsDermaAppointments(current.getId());

        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

    @GetMapping(value = "/examinationsNotFinished", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDermaAppointmentsNotFinished(Principal user){
        User current = userService.findByEmail(user.getName());
        List<ExaminationDTO> dtos = examinationService.getPatientsDermaAppointmentsNotFinished(current.getId());

        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

    @GetMapping(value = "/consultations", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacistAppointments(Principal user) {
        User current = userService.findByEmail(user.getName());
        List<ConsultationDTO> dtos = consultationService.getPatientsConsultations(current.getId());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/consultationsNotFinished", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacistAppointmentsNotFinished(Principal user) {
        User current = userService.findByEmail(user.getName());
        List<ConsultationDTO> dtos = consultationService.getPatientsConsultationsNotFinished(current.getId());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getCurrentPatient(Principal user) {
        PatientService patientService = (PatientService) serviceFactory.getUserService(UserRoles.PATIENT);

        User current = userService.findByEmail(user.getName());
        PatientDTO dto = patientService.getPatient(current.getId());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/eReceipts", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getEreceipts(Principal user){
        User current = userService.findByEmail(user.getName());
        List<EReceiptDTO> dtos = eRecepitService.findByPatient(current.getId());

        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @GetMapping(value = "/reservedDrugs", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getReservedDrugs(Principal user){
        User current = userService.findByEmail(user.getName());
        List<DrugDTO> dtos = drugService.getPatientDrugsFromReservation(current.getId());

        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @GetMapping(value = "/eReceiptDrugs", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getEReceiptDrugs(Principal user){
        User current = userService.findByEmail(user.getName());
        List<DrugDTO> dtos = drugService.getPatientDrugsFromEReceipt(current.getId());

        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

}
