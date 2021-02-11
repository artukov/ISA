package isa.project.pharmacyapp.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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

    @Autowired
    private PharmacyService pharmacyService;

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

    @GetMapping(value = "/getFreeConsultationPharmacies",/*consumes = MediaType.APPLICATION_JSON_VALUE,*/ produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getFreeConsultationPharmacies(@RequestParam("dateTime") String dateTime){


        String[] dateTimeParts = dateTime.split(" ");
        String dateString = dateTimeParts[0];
        String timeString = dateTimeParts[1];
        String timeZone = dateTimeParts[2];

        String[] dateParts = dateString.split("-");

        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];

        String[] timeParts = timeString.split(":");

        String hours = timeParts[0];
        String minutes = timeParts[1];
        String seconds = timeParts[2];

        Date newDate = new Date(Integer.parseInt(year) ,Integer.parseInt(month),Integer.parseInt(day),Integer.parseInt(hours),Integer.parseInt(minutes),Integer.parseInt(seconds));


        List<PharmacyDTO> dtos = pharmacyService.getPharmaciesWithFreeConsultation(newDate);

        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @PostMapping(value = "/consultation/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> createNewConsultationAppointment(@RequestBody ConsultationDTO consultationDTO, Principal user){

//        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);
//
//        User current = pharmacistService.findByEmail(user.getName());
//
//        if(current.getRole() == UserRoles.PATIENT){
//            return new ResponseEntity<>("Not allowed", HttpStatus.UNAUTHORIZED);
//        }

        //consultationDTO.setPharmacistID(current.getId());
        consultationDTO.setFinished(false);
        /**
         * TODO
         * Create consultation context on front
         * */

        try {
            consultationService.createNewConsultation(consultationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/freeExaminations/{pharmacyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getFreeExaminations(@PathVariable("pharmacyId") Long pharmacyId){

        List<ExaminationDTO> dtos = examinationService.getFreeExaminationsFromPharmacy(pharmacyId);

        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @PutMapping(value = "/examination/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> modifyExamination(@RequestBody ExaminationDTO examinationDTO, Principal user){
        try {
            examinationService.modifyExamination(examinationDTO);
        }
        catch (NoSuchElementException ele){
            ele.printStackTrace();
            return new ResponseEntity<>("ExaminationController::modifyExamination " +
                    "examination with given id does not exists",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("ExaminationController::modifyExamination Server error"
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
