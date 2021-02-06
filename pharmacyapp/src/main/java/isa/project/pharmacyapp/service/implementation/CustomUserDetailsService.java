package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.repository.UserRepository;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final Log LOGGER = LogFactory.getLog(getClass());

    /**
     * Overriden method that will return user based on the passed email
     * Because it an override method, the parameter is called {@param username}
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String email = username;
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
        }

        return user;
    }

    /**
     * Method that allows user to change password
     * */
    public void changePassword(String oldPassword, String newPassword) throws Exception {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String email = currentUser.getName();

        if(authenticationManager != null){
            LOGGER.debug("Re-authenticating user '" + email + "' for password change request.");
           try{
               authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, oldPassword));
           }
           catch (Exception e){
               throw new Exception("Old password is not correct");
           }
        }
        else {
            LOGGER.debug("No authentication manager set. can't change Password!");

            return;
        }
        LOGGER.debug("Changing password for user '" + email + "'");

        User user = (User) loadUserByUsername(email);

        //before we insert new password we must encode it
        //password should not be kept as plain text
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }


}
