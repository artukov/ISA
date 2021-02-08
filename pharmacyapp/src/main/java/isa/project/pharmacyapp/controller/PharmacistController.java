package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.*;
import isa.project.pharmacyapp.model.Pharmacist;
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

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping(value = "/pharmacist")
public class PharmacistController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceFactory serviceFactory;

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private DrugService drugService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/findAllByPharmacy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllPharmacyPharmacists(@PathVariable("id") Long id){

        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);

        List<PharmacistDTO> pharmacistDTOList = pharmacistService.findAllByPharmacy(id);

        return new ResponseEntity<>(pharmacistDTOList, HttpStatus.OK);

    }



    @GetMapping(value = "/findPatients/{pharmaId}/{orderCondition}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> findAllPharmacistPatients(@PathVariable("pharmaId") Long pharmaId, @PathVariable("orderCondition") String orderCondition ){
        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);

        List<PatientDTO> patientDTOList = pharmacistService.findAllPharmacistPatients(pharmaId, orderCondition);

        return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllPatients", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllPatients(){
        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);
        List<PatientDTO> patientDTOList = pharmacistService.getAllPatients();

        return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllPatients/{firstName}/{lastName}",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPatientsByNameAndSurname(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);
        List<PatientDTO> patientDTOList = pharmacistService.findPatientbyNameAndSurname(firstName,lastName);

        return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/calendar", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacistCalendar(Principal user){
        User current = userService.findByEmail(user.getName());
        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);
        List<CalendarDTO> calendar = pharmacistService.getPharmacistCalendar(current.getId());

        return new ResponseEntity<>(calendar,HttpStatus.OK);
    }

    @PostMapping(value="/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> createNewPharmacist(@RequestBody PharmacistDTO pharmacistDTO){

        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);
        pharmacistDTO.setPassword(passwordEncoder.encode(pharmacistDTO.getPassword()));

        try {
            pharmacistService.createNewPharmacist(pharmacistDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> modifyDermatologist(@RequestBody PharmacistDTO pharmacistDTO, Principal user){

        User current = userService.findByEmail(user.getName());
        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);

        try {
            pharmacistService.modifyPharmacist(current.getId(),pharmacistDTO);
        }
        catch (NoSuchElementException ele){
            ele.printStackTrace();
            return new ResponseEntity<>("PharmacistController::modifyPharmacist " +
                    "Pharmacist with given id does not exists",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("PharmacistController::modifyPharmacist Server error"
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/pharmacy/{id}")
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> deletePharmacistFromPharmacy(@PathVariable("id")Long id){

        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);

        try {
            pharmacistService.deletePharmacistById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/appointment/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> createNewConsultationAppointment(@RequestBody ConsultationDTO consultationDTO, Principal user){

        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);

        User current = pharmacistService.findByEmail(user.getName());

        if(current.getRole() == UserRoles.PATIENT){
            return new ResponseEntity<>("Not allowed", HttpStatus.UNAUTHORIZED);
        }

        consultationDTO.setPharmacistID(current.getId());
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

    @PutMapping(value = "/consultation/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> modifyConsultation(@RequestBody ConsultationDTO consultationDTO, Principal user){

        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);

        User current = pharmacistService.findByEmail(user.getName());

        if(current.getRole() == UserRoles.PATIENT){
            return new ResponseEntity<>("Not allowed", HttpStatus.UNAUTHORIZED);
        }

        consultationDTO.setPharmacistID(current.getId());
        consultationDTO.setFinished(true);
        try {
            consultationService.modifyConsultation(consultationDTO);
        }
        catch (NoSuchElementException ele){
            ele.printStackTrace();
            return new ResponseEntity<>(ele.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage()
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
            return new ResponseEntity<>("PharmacistController::addPenalty " +
                    "Patient with given id does not exists",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("PharmacistController::addPenalty Server error"
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/dispense", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> dispenseDrug(@RequestBody Long id){
        try {
            reservationService.dispenseDrug(id);
        }
        catch (NoSuchElementException ele){
            ele.printStackTrace();
            return new ResponseEntity<>("PharmacistController::dispenseDrug " +
                    "Reservation with given id does not exists",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("PharmacistController::dispenseDrug Server error"
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/reservation/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getReservation(@PathVariable("reservationId") Long id, Principal user){

        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);

        Pharmacist current = (Pharmacist) pharmacistService.findByEmail(user.getName());

        Long pharmacyId = current.getPharmacy().getId();

        ReservationDTO dto = reservationService.getByIdAndPharmacy(id, pharmacyId);

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getCurrentPharmacist(Principal user){
        User current = userService.findByEmail(user.getName());
        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);
        PharmacistDTO dto = pharmacistService.findById(current.getId());

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping(value = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacistPatients(Principal user){
        User current = userService.findByEmail(user.getName());
        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);
        List<PatientDTO> patientDTOList = pharmacistService.getPharmacistPatients(current.getId());

        return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/patientsDistinct", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacistPatientsDistinct(Principal user){
        User current = userService.findByEmail(user.getName());
        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);
        List<PatientDTO> patientDTOList = pharmacistService.getPharmacistPatientsDistinct(current.getId());

        return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/findConsultation/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> findConsultation(@PathVariable("patientId") Long patientId, @RequestParam("dateTime") Long dateTime){
        Date newDate = new Date();
        newDate.setTime(dateTime);
        ConsultationDTO dto = null;
        try{
            dto =  consultationService.findConsultation(patientId,newDate);
        }catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping(value = "/checkAllergy/{patientId}/{drugId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> checkAllergy(@PathVariable("patientId") Long patientId, @PathVariable("drugId") Long drugId){
        List<DrugDTO> dtos = new ArrayList<>();
        try{
            dtos = drugService.checkForAllergy(patientId,drugId);

        } catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }


}
