package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.CalendarDTO;
import isa.project.pharmacyapp.dto.DermatologistDTO;
import isa.project.pharmacyapp.dto.PatientDTO;
import isa.project.pharmacyapp.dto.PharmacistDTO;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.service.DermatologistService;
import isa.project.pharmacyapp.service.PharmacistService;
import isa.project.pharmacyapp.service.UserService;
import isa.project.pharmacyapp.user_factory.UserServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping(value = "/findAllByPharmacy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllPharmacyPharmacists(@PathVariable("id") Long id){

        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);

        List<PharmacistDTO> pharmacistDTOList = pharmacistService.findAllByPharmacy(id);

        return new ResponseEntity<>(pharmacistDTOList, HttpStatus.OK);

    }

    @DeleteMapping(value = "/delete/pharmacy/{id}")
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> deletePharmacistFromPharmacy(@PathVariable("id")Long id){

        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);

        try {
            pharmacistService.deletePharmacistById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
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
}
