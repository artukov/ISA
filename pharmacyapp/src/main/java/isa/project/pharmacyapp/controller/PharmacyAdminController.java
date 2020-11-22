package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.PharmacyAdminDTO;
import isa.project.pharmacyapp.model.PharmacyAdmin;
import isa.project.pharmacyapp.service.PharmacyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.awt.*;
import java.util.NoSuchElementException;

@Controller
@RestController
@RequestMapping(value = "/pharmacyadmin")
@CrossOrigin
public class PharmacyAdminController {

    @Autowired
    private PharmacyAdminService adminService;

    @GetMapping(value = "/find/{adminId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable("adminId") Long adminId){

        PharmacyAdminDTO adminDTO = adminService.findById(adminId);
        if(adminDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(adminDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/findByPharmacy/{pharmacyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByPharmacyId(@PathVariable("pharmacyId")Long pharmacyId){

        return new ResponseEntity<>(adminService.findAdminsOfPharmacy(pharmacyId),HttpStatus.OK);
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewPharmacyAdmin(@RequestBody PharmacyAdminDTO adminDTO){

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
    public ResponseEntity<?> modifyPharmacyAdmin(@RequestBody PharmacyAdminDTO adminDTO, @PathVariable("adminId") Long adminId){

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
    public ResponseEntity<?> deletePharmacyAdmin(@PathVariable("adminId") Long adminId){

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


}
