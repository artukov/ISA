package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.model.UserRoles;

public interface UserService {

    public abstract User findByEmail(String email);
    public abstract User saveNewUser(UserDTO userDTO) throws Exception;


}
