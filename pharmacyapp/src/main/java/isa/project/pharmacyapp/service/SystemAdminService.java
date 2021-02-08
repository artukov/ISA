package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.model.SystemAdmin;

public interface SystemAdminService extends UserService{
    void modifySystemAdmin(Long id, UserDTO adminDTO) throws Exception;

    void saveSystemAdmin(SystemAdmin admin , UserDTO adminDTO) throws Exception;

    void createNewSystemAdmin(UserDTO userDTO) throws Exception;
}
