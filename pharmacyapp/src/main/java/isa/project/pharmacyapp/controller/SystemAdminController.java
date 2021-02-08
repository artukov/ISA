package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.service.SystemAdminService;
import isa.project.pharmacyapp.user_factory.UserServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/systemadmin")
@CrossOrigin
public class SystemAdminController {

    private static final String AUTHORITY = "hasAuthority('ADMIN')";


    @Autowired
    private UserServiceFactory serviceFactory;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping(value="/new",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> postNewSystemAdmin(@RequestBody UserDTO userDTO){

        SystemAdminService adminService = (SystemAdminService) serviceFactory.getUserService(UserRoles.SYSTEM_ADMIN);
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        try {
            adminService.createNewSystemAdmin(userDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/modify/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> modifySystemAdmin(@RequestBody UserDTO adminDTO, @PathVariable("id") Long id){

        SystemAdminService adminService = (SystemAdminService) serviceFactory.getUserService(UserRoles.SYSTEM_ADMIN);

        try {
            adminService.modifySystemAdmin(id,adminDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }




}
