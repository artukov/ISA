package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.dto.UserTokenState;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Array;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<?> currentUser(Principal user){
        System.out.println("User current " + user);
        User current = userService.findByEmail(user.getName());
        System.out.println("User current \t\t\t\t\t\t\t" + current);
        UserDTO dto = UserDTO.user2DTO(current);

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<?> getUserRoles(){
        return new ResponseEntity<>(UserRoles.values(),HttpStatus.OK);
    }

    @GetMapping(value="/userPage")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<?> redirectToUserPage(Principal user, UriComponentsBuilder builder){
        User current = userService.findByEmail(user.getName());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Expose-Headers","Location");
        builder.port(3000);
        switch (current.getRole()){
            case PHARMACY_ADMIN : headers.setLocation(builder.path("/pharmacyAdmin").buildAndExpand().toUri()); break;
            case DERMATOLOGIST : headers.setLocation(builder.path("/dermatologist").buildAndExpand().toUri()); break;
            case PHARMACIST : headers.setLocation(builder.path("/pharmacist").buildAndExpand().toUri()); break;
            case PATIENT:  headers.setLocation(builder.path("/patient").buildAndExpand().toUri()); break;
            case SUPPLIER : headers.setLocation(builder.path("/supplier").buildAndExpand().toUri()); break;
            case SYSTEM_ADMIN: headers.setLocation(builder.path("/systemAdmin").buildAndExpand().toUri()); break;
            default : headers.setLocation(builder.path("/home").buildAndExpand().toUri()); break;
        }

        return new ResponseEntity<>(null,headers,HttpStatus.OK);

    }

}
