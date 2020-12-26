package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.PharmacyAdminDTO;
import isa.project.pharmacyapp.model.TimeSpam;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.service.DermatologistService;
import isa.project.pharmacyapp.service.PharmacistService;
import isa.project.pharmacyapp.service.PharmacyAdminService;
import isa.project.pharmacyapp.user_factory.UserServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.NoSuchElementException;

@Controller
@RestController
@RequestMapping(value = "/pharmacyadmin")
@CrossOrigin
public class PharmacyAdminController {

    private static final String AUTHORITY = "hasAuthority('USER')";

//    @Autowired
//    private PharmacyAdminService adminService;

    @Autowired
    private UserServiceFactory serviceFactory;

    @GetMapping(value = "/find/{adminId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> findById(@PathVariable("adminId") Long adminId){

        PharmacyAdminService adminService = (PharmacyAdminService) serviceFactory.getUserService(UserRoles.PHARMACY_ADMIN);

        PharmacyAdminDTO adminDTO = adminService.findById(adminId);
        if(adminDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(adminDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/findByPharmacy/{pharmacyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> findByPharmacyId(@PathVariable("pharmacyId")Long pharmacyId){

        PharmacyAdminService adminService = (PharmacyAdminService) serviceFactory.getUserService(UserRoles.PHARMACY_ADMIN);

        return new ResponseEntity<>(adminService.findAdminsOfPharmacy(pharmacyId),HttpStatus.OK);
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> createNewPharmacyAdmin(@RequestBody PharmacyAdminDTO adminDTO){

        PharmacyAdminService adminService = (PharmacyAdminService) serviceFactory.getUserService(UserRoles.PHARMACY_ADMIN);

        try {
            adminService.createNewPharmacyAdmin(adminDTO);
        } catch (NoSuchElementException ele){
            ele.printStackTrace();
            return new ResponseEntity<>("PharmacyAdminController:createNewPharmacyAdmin " +
                    "PharmacyAdmin with given id does not exists",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("PharmacyAdminController::createNewPharmacyAdmin Server error"
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(value = "/modify/{adminId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> modifyPharmacyAdmin(@RequestBody PharmacyAdminDTO adminDTO, @PathVariable("adminId") Long adminId){

        PharmacyAdminService adminService = (PharmacyAdminService) serviceFactory.getUserService(UserRoles.PHARMACY_ADMIN);

        try {
            adminService.modifyPharmacyAdmin(adminId,adminDTO);
        }
        catch (NoSuchElementException ele){
            ele.printStackTrace();
            return new ResponseEntity<>("PharmacyAdminController::modifyPharmacyAdmin " +
                    "PharmacyAdmin with given id does not exists",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("PharmacyAdminController::modifyPharmacyAdmin Server error"
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/delete/{adminId}")
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> deletePharmacyAdmin(@PathVariable("adminId") Long adminId){

        PharmacyAdminService adminService = (PharmacyAdminService) serviceFactory.getUserService(UserRoles.PHARMACY_ADMIN);

        try {
            adminService.deletePharmacyAdmin(adminId);
        }catch (NoSuchElementException ele){
            ele.printStackTrace();
            return new ResponseEntity<>("PharmacyAdminController::deletePharmacyAdmin " +
                    "PharmacyAdmin with given id does not exists",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("PharmacyAdminController::deletePharmacyAdmin " +
                    "Server error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/avgPharmacyRating/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacyAverageRating(@PathVariable("id") Long pharmacyId){

        PharmacyAdminService adminService = (PharmacyAdminService) serviceFactory.getUserService(UserRoles.PHARMACY_ADMIN);

        Double avg = null;

        try {
            avg =  adminService.getAvgRating(pharmacyId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(getClass().getName()+"::getAverageRating server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(avg,HttpStatus.OK);
    }

    @GetMapping(value = "/avgPharmacistRating/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacistAverageRating(@PathVariable("id") Long id){
        PharmacistService pharmacistService = (PharmacistService) serviceFactory.getUserService(UserRoles.PHARMACIST);

        Double avg = null;

        try {
            avg = pharmacistService.getAvgRating(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(getClass().getName()+"::getPharmacistAverageRating server error", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(avg,HttpStatus.OK);


    }

    @GetMapping(value = "/avgDermaRating/{dermaID}/{pharmacyID}")
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDermatologistAvgRatings(@PathVariable("dermaID") Long dermaID,
                                                        @PathVariable("pharmacyID") Long pharmacyID){
        DermatologistService dermatologistService = (DermatologistService) serviceFactory.getUserService(UserRoles.DERMATOLOGIST);

        Double avg = null;

        try {
             avg = dermatologistService.getAvgRatings(dermaID, pharmacyID);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(getClass().getName()+"::getDermatologistAvgRatings server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(avg, HttpStatus.OK);
    }

    @GetMapping(value = "/statistics/examination/{timespam}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacyExaminationStatistics(@PathVariable("timespam") TimeSpam timeSpam,
                                                              @PathVariable("id") Long id ){
        PharmacyAdminService adminService = (PharmacyAdminService) serviceFactory.getUserService(UserRoles.PHARMACY_ADMIN);

        ArrayList<Double> stats = (ArrayList<Double>) adminService.getExaminationStatistics(id, timeSpam);
//        System.out.println(stats.size());
        return new ResponseEntity<>(stats,HttpStatus.OK);
    }



}
