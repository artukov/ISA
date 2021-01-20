package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.CalendarDTO;
import isa.project.pharmacyapp.dto.DermatologistDTO;
import isa.project.pharmacyapp.dto.PatientDTO;
import isa.project.pharmacyapp.dto.PharmacyAdminDTO;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.service.DermatologistService;
import isa.project.pharmacyapp.service.PharmacistService;
import isa.project.pharmacyapp.service.PharmacyAdminService;
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
@RequestMapping(value = "/dermatologist")
public class DermatologistController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceFactory serviceFactory;

    @GetMapping(value = "/findByPharmacy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDermatologistInPharmacy(@PathVariable("id")Long id){

        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);

        List<DermatologistDTO> dermatologistDTOList = dermatologistService.findAllByPharmacy(id);

        return new ResponseEntity<>(dermatologistDTOList, HttpStatus.OK);

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
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/findPatients/{dermaId}/{orderCondition}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> findAllPatients(@PathVariable("dermaId") Long dermaId, @PathVariable("orderCondition") String orderCondition ){
        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);

        List<PatientDTO> patientDTOList = dermatologistService.findAllPatients(dermaId, orderCondition);

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
}
