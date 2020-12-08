package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.exception.ResourceConflictException;
import isa.project.pharmacyapp.model.Authority;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.model.UserTokenState;
import isa.project.pharmacyapp.security.TokenUtils;
import isa.project.pharmacyapp.security.authetication.JwtAuthenticationRequest;
import isa.project.pharmacyapp.service.UserService;
import isa.project.pharmacyapp.service.implementation.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>  createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
                                                        HttpServletResponse response) throws AuthenticationException, IOException {
        Authentication authentication = null;
        try{
            authentication =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword())
            );
        }
        catch (Exception e){
            return new ResponseEntity<>("You have entered wrong email or password", HttpStatus.BAD_REQUEST);
        }



//        SecurityContext ctx = SecurityContextHolder.createEmptyContext();
//        SecurityContextHolder.setContext(ctx);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        System.out.println("Login user:" + user.getUsername());

        Collection<?> roles = user.getAuthorities();
        Authority role = (Authority) roles.iterator().next();

        System.out.println("Login user role: "+ role.getAuthority());

        String JWT = tokenUtils.generateToken(user, role);
        int expires = tokenUtils.getExpiredIn();

        return new ResponseEntity<>(new UserTokenState(JWT,expires), HttpStatus.OK);
    }

    @PostMapping(value = "/singup", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> singUpAnUser(@RequestBody UserDTO userDTO, UriComponentsBuilder builder){
        User user = this.userService.findByEmail(userDTO.getEmail());
        if(user != null){
            throw new ResourceConflictException("User with given email already exists", user.getId());
        }
        /**
         * TODO
         * Sign up of an user
         * Factory Pattern
         * */
        user = this.userService.saveUser(userDTO);
        /**
         * TODO
         * Redirection after the user sings up
         *      HttpHeaders headers = new HttpHeaders();
         * 		headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
         * */

        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> refreshUsersToken(HttpServletRequest request){
        String token = tokenUtils.getToken(request);
        String email = tokenUtils.getEmailFromToken(token);
        User user = (User) userDetailsService.loadUserByUsername(email);

        if(tokenUtils.canTokenBeRefreshed(token,user.getLastPasswordResetDate())){
            String refreshedToken = tokenUtils.refreshToken(token);
            int expires = tokenUtils.getExpiredIn();

            return new ResponseEntity<>(new UserTokenState(refreshedToken,expires),HttpStatus.OK);
        }
        else{
            UserTokenState userTokenState = new UserTokenState();
            return new ResponseEntity<>(userTokenState,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/change-password",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger changer){
        userDetailsService.changePassword(changer.oldPassword,changer.newPassword);

        Map<String, String > result = new HashMap<String, String>();
        result.put("result", "success");

        return new ResponseEntity<>(result,HttpStatus.ACCEPTED);


    }

    static class PasswordChanger{
        private String oldPassword;
        private String newPassword;
    }











}
