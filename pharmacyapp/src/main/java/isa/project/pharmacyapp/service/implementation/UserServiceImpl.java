package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.repository.UserRepository;
import isa.project.pharmacyapp.service.AuthorityService;
import isa.project.pharmacyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String CLASS_NAME = "UserServiceImpl::";
    private static final String DOES_NOT_EXISTS = " user does not exists with given ";
    private static final String UNSUCCESSFUL_EXECUTION = "did not execute successfully";

    @Override
    public User findByEmail(String email) throws UsernameNotFoundException {
        System.out.println("Email \t\t\t\t" + email);
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw  new NoSuchElementException(CLASS_NAME + "findByEmail" + DOES_NOT_EXISTS);
        }

        return user;
    }

    @Override
    public User saveNewUser(UserDTO userDTO) throws Exception {
        /**
         * TODO
         * Save unregistered user
         * */
        return null;
    }
}
