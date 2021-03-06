package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.exception.ResourceConflictException;
import isa.project.pharmacyapp.model.Authority;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.dto.UserTokenState;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.model.VerificationToken;
import isa.project.pharmacyapp.registration_event.IUserService;
import isa.project.pharmacyapp.registration_event.OnRegistrationCompleteEvent;
import isa.project.pharmacyapp.security.TokenUtils;
import isa.project.pharmacyapp.security.authetication.JwtAuthenticationRequest;
import isa.project.pharmacyapp.service.PatientService;
import isa.project.pharmacyapp.service.UserService;
import isa.project.pharmacyapp.service.implementation.CustomUserDetailsService;
import isa.project.pharmacyapp.user_factory.UserServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

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
    private UserServiceFactory serviceFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private IUserService iUserService;


    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>  createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
                                                        HttpServletResponse response, UriComponentsBuilder builder,
                                                            HttpServletRequest request) throws AuthenticationException, IOException {
        Authentication authentication = null;
        try{
            authentication =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword())
            );
        }
        catch (Exception e){
            return new ResponseEntity<>("You have entered wrong email or password", HttpStatus.BAD_REQUEST);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();


        Collection<?> roles = user.getAuthorities();
        Authority role = (Authority) roles.iterator().next();


        String JWT = tokenUtils.generateToken(user, role);
        int expires = tokenUtils.getExpiredIn();

        /**setting headers of the response*/
        HttpHeaders headers = new HttpHeaders();

        /**Allow access to front environment to access location header*/
        headers.add("Access-Control-Expose-Headers","Location");
        /**Finding witch port is the Origin of the request*/
        String[] origin = request.getHeader("Origin").split("http://localhost:");

        /** Setting up  port of the new uri */
        builder.port(origin[1]);

        if(user.getLastPasswordResetDate() == null){
            headers.setLocation(builder.path("/reset-password").buildAndExpand().toUri());
            return new ResponseEntity<>(new UserTokenState(JWT,expires), headers,HttpStatus.OK);
        }

        switch (user.getRole()){
            case PHARMACY_ADMIN : headers.setLocation(builder.path("/pharmacyAdmin").buildAndExpand().toUri()); break;
            case DERMATOLOGIST : headers.setLocation(builder.path("/dermatologist").buildAndExpand().toUri()); break;
            case PHARMACIST : headers.setLocation(builder.path("/pharmacist").buildAndExpand().toUri()); break;
            case PATIENT:  headers.setLocation(builder.path("/patient").buildAndExpand().toUri()); break;
            case SUPPLIER : headers.setLocation(builder.path("/supplier").buildAndExpand().toUri()); break;
            case SYSTEM_ADMIN: headers.setLocation(builder.path("/systemAdmin").buildAndExpand().toUri()); break;
            default : headers.setLocation(builder.path("/home").buildAndExpand().toUri()); break;
        }


        return new ResponseEntity<>(new UserTokenState(JWT,expires), headers,HttpStatus.OK);
    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> singUpAnUser(@RequestBody UserDTO userDTO, UriComponentsBuilder builder, HttpServletRequest request){
        /**
         * Factory Pattern
         * */
        UserService service = this.serviceFactory.getUserService(userDTO.getRole());
        User user = service.findByEmail(userDTO.getEmail());

        if(user != null){
            throw new ResourceConflictException("User with given email already exists", user.getId());
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        try {
            user  = service.saveNewUser(userDTO);

            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user,request.getLocale(),appUrl));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(this.getClass().getName() +"::singUpAnUser saving user",HttpStatus.INTERNAL_SERVER_ERROR);
        }


        HttpHeaders headers = new HttpHeaders();
//        headers.add("Access-Control-Expose-Headers","Location");
//        String[] origin = request.getHeader("Origin").split("http://localhost:");
//        builder.port(origin[1]);
//        headers.setLocation(builder.path("/home").buildAndExpand().toUri());

        return new ResponseEntity<>(user, headers,HttpStatus.CREATED);

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

    @PostMapping(value = "/change-password",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger changer,
                                            HttpServletRequest request, UriComponentsBuilder builder){
        try {
            userDetailsService.changePassword(changer.oldPassword,changer.newPassword);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Expose-Headers","Location");
        String[] origin = request.getHeader("Origin").split("http://localhost:");
        builder.port(origin[1]);


        headers.setLocation(builder.path("/login").buildAndExpand().toUri());

        Map<String, String> result = new HashMap<String, String>();
        result.put("result", "success");

        return new ResponseEntity<>(result,headers,HttpStatus.ACCEPTED);


    }

    static class PasswordChanger{
        public String oldPassword;
        public String newPassword;

        public PasswordChanger() {
        }
    }

    @GetMapping(value="/registrationConfirm")
    public ResponseEntity<?> confirmRegistration(WebRequest request,@RequestParam("token") String token, UriComponentsBuilder builder){
        Locale locale = request.getLocale();

        VerificationToken verificationToken = iUserService.getVerificationToken(token);
        if(verificationToken == null){
            return new ResponseEntity<>("invalid token",HttpStatus.BAD_REQUEST);
        }

        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0){
            return new ResponseEntity<>("token has expired", HttpStatus.BAD_REQUEST);
        }

        user.setEnabled(true);
        PatientService patientService = (PatientService) serviceFactory.getUserService(UserRoles.PATIENT);
        try {
            patientService.saveNewUser(UserDTO.user2DTO(user));
            iUserService.deleteUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        iUserService.saveRegisteredUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Expose-Headers","Location");
        builder.port(3000);
        headers.setLocation(builder.path("/login").buildAndExpand().toUri());
        return new ResponseEntity<>(null,headers,HttpStatus.PERMANENT_REDIRECT);
    }

    @PostMapping(value = "/logout")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> logout(Principal user, UriComponentsBuilder builder){
        SecurityContextHolder.getContext().setAuthentication(null);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Expose-Headers","Location");
        builder.port(3000);
        headers.setLocation(builder.path("/login").buildAndExpand().toUri());
        return new ResponseEntity<>(null,headers,HttpStatus.OK);
    }











}
