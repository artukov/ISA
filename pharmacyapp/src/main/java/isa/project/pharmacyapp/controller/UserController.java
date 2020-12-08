package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<?> currentUser(Principal user){
        System.out.println("User current " + user);
        User current = userService.findByEmail(user.getName());

        return new ResponseEntity<>(current, HttpStatus.OK);

    }

}
