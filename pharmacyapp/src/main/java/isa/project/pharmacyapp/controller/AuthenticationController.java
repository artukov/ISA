package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.exception.ResourceConflictException;
import isa.project.pharmacyapp.model.Authority;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.dto.UserTokenState;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.security.TokenUtils;
import isa.project.pharmacyapp.security.authetication.JwtAuthenticationRequest;
import isa.project.pharmacyapp.service.UserService;
import isa.project.pharmacyapp.service.implementation.CustomUserDetailsService;
import isa.project.pharmacyapp.user_factory.UserFactory;
import isa.project.pharmacyapp.user_factory.UserServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
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
    private UserServiceFactory serviceFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;


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

        /**Allow access to front environment to acces location header*/
        headers.add("Access-Control-Expose-Headers","Location");
        /**Finding witch port is the Origin of the request*/
        String[] origin = request.getHeader("Origin").split("http://localhost:");

        /** Setting up  port of the new uri */
        builder.port(origin[1]);


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
    public ResponseEntity<?> singUpAnUser(@RequestBody UserDTO userDTO, UriComponentsBuilder builder){
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
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(this.getClass().getName() +"::singUpAnUser saving user",HttpStatus.INTERNAL_SERVER_ERROR);
        }

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

        Map<String, String> result = new HashMap<String, String>();
        result.put("result", "success");

        return new ResponseEntity<>(result,HttpStatus.ACCEPTED);


    }

    static class PasswordChanger{
        private String oldPassword;
        private String newPassword;
    }











}
