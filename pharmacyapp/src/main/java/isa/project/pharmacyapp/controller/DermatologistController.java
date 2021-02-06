package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.*;
import isa.project.pharmacyapp.exception.DeletingDermatologistException;
import isa.project.pharmacyapp.model.Patient;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping(value = "/dermatologist")
public class DermatologistController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceFactory serviceFactory;

    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/findByPharmacy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDermatologistInPharmacy(@PathVariable("id")Long id){

        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);

        List<DermatologistDTO> dermatologistDTOList = dermatologistService.findAllByPharmacy(id);

        return new ResponseEntity<>(dermatologistDTOList, HttpStatus.OK);

    }

    @GetMapping(value = "/findNotInPharmacy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDermatologistNotInPharmacy(@PathVariable("id")Long pharmacyID){
        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);

        List<DermatologistDTO> dermatologistDTOList = null;
        try {
            dermatologistDTOList = dermatologistService.findAllNotInPharmacy(pharmacyID);
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(dermatologistDTOList, HttpStatus.OK);

    }


    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getCurrentDermatologist(Principal user){
        User current = userService.findByEmail(user.getName());
        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);
        DermatologistDTO dto = dermatologistService.findById(current.getId());

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping(value = "/findByID/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDermatologist(@PathVariable("id") Long id){
        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);

        DermatologistDTO dto = dermatologistService.findById(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @DeleteMapping(value = "/delete/pharmacy/{dermaID}/{pharmacyID}")
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> deleteDermatologistFromPharmacy(@PathVariable("dermaID") Long dermaID,
                                                             @PathVariable("pharmacyID") Long pharmacyID){

        DermatologistService service = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);

        try {
            service.deleteDermatologistPharmacy(dermaID, pharmacyID);
        }
        catch (DeletingDermatologistException dde){
            return new ResponseEntity<>(dde.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/findPatients/{dermaId}/{orderCondition}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> findAllDermaPatients(@PathVariable("dermaId") Long dermaId, @PathVariable("orderCondition") String orderCondition ){
        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);

        List<PatientDTO> patientDTOList = dermatologistService.findAllDermaPatients(dermaId, orderCondition);

        return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
    }
    @GetMapping(value = "/getAllPatients", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllPatients(){
        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);
        List<PatientDTO> patientDTOList = dermatologistService.getAllPatients();


        return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDermaPatients(Principal user){
        User current = userService.findByEmail(user.getName());
        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);
        List<PatientDTO> patientDTOList = dermatologistService.getDermaPatients(current.getId());

        return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/patientsDistinct", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDermaPatientsDistinct(Principal user){
        User current = userService.findByEmail(user.getName());
        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);
        List<PatientDTO> patientDTOList = dermatologistService.getDermaPatientsDistinct(current.getId());

        return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllPatients/{firstName}/{lastName}",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPatientsByNameAndSurname(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);
        List<PatientDTO> patientDTOList = dermatologistService.findPatientbyNameAndSurname(firstName,lastName);

        return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/calendar", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDermatologistCalendar(Principal user){
        User current = userService.findByEmail(user.getName());
        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);
        List<CalendarDTO> calendar = dermatologistService.getDermatologistCalendar(current.getId());
        return new ResponseEntity<>(calendar,HttpStatus.OK);
    }

    @PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> modifyDermatologist(@RequestBody DermatologistDTO dermatologistDTO, Principal user){

        User current = userService.findByEmail(user.getName());
        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);

        try {
            dermatologistService.modifyDermatologist(current.getId(),dermatologistDTO);
        }
        catch (NoSuchElementException ele){
            ele.printStackTrace();
            return new ResponseEntity<>("DermatologistController::modifyDermatologist " +
                    "Dermatologist with given id does not exists",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DermatologistController::modifyDermatologist Server error"
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/appointment/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> createNewExaminationAppointment(@RequestBody ExaminationDTO examinationDTO, Principal user){

        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);

        User current = userService.findByEmail(user.getName());

        if(current.getRole() == UserRoles.PATIENT){
            return new ResponseEntity<>("Not allowed", HttpStatus.UNAUTHORIZED);
        }

        examinationDTO.setDermatologist_id(current.getId());
        examinationDTO.setFinished(false);
        /**
         * TODO
         * Create examination context on front
         * */

        try {
            examinationService.createNewExamination(examinationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/examination/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> modifyExamination(@RequestBody ExaminationDTO examinationDTO, Principal user){

        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);

        User current = dermatologistService.findByEmail(user.getName());

        if(current.getRole() == UserRoles.PATIENT){
            return new ResponseEntity<>("Not allowed", HttpStatus.UNAUTHORIZED);
        }

        examinationDTO.setDermatologist_id(current.getId());
        examinationDTO.setFinished(true);
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
    @PutMapping(value = "/addPenalty/{id}")
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> addPenalty(@PathVariable("id") Long id){
        try {
            patientService.addPenalty(id);
        }
        catch (NoSuchElementException ele){
            ele.printStackTrace();
            return new ResponseEntity<>("PatientController::modifyPatient " +
                    "Patient with given id does not exists",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("PatientController::modifyPatient Server error"
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "/pharmacies/{dermaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDermaPharmacies(@PathVariable("dermaId") Long dermaId){
        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);

        List<PharmaDermaDTO> dto = dermatologistService.getDermaPharmacies(dermaId);

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping(value = "/findExamination/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> findExamination(@PathVariable("patientId") Long patientId, @RequestParam("dateTime") Long dateTime){
        Date newDate = new Date();
        newDate.setTime(dateTime);
        ExaminationDTO dto = null;
        try{
            dto =  examinationService.findExamination(patientId,newDate);
        }catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


}
