package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.model.User;

import java.util.List;

public interface UserService {

    public abstract User findById(Long id);
    public abstract User findByEmail(String email);
    public abstract List<User> findAll();
    public abstract User saveUser(UserDTO userDTO);

}
