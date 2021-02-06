package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.model.Address;
import isa.project.pharmacyapp.model.SystemAdmin;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.repository.AddressRepository;
import isa.project.pharmacyapp.repository.SystemAdminRepository;
import isa.project.pharmacyapp.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemAdminServiceImpl implements SystemAdminService {

    @Autowired
    private SystemAdminRepository systemAdminRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void modifySystemAdmin(Long id, UserDTO adminDTO) throws Exception {
        SystemAdmin admin = systemAdminRepository.findById(id).orElseThrow(
                ()-> new Exception("Admin with given id does not exists"));
        this.saveSystemAdmin(admin, adminDTO);
    }

    @Override
    public void saveSystemAdmin(SystemAdmin admin, UserDTO adminDTO) throws Exception {
        UserDTO.dto2User(admin,adminDTO);
        Address address = addressRepository.findById(adminDTO.getAddress_id()).orElseThrow(
                ()->new Exception("Address with given id does not exists"));
        try{
            systemAdminRepository.save(admin);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("saving system admin");
        }
    }

    @Override
    public SystemAdmin findByEmail(String email) {
        return systemAdminRepository.findByEmail(email);
    }

    @Override
    public User saveNewUser(UserDTO userDTO) throws Exception {

        this.saveSystemAdmin(new SystemAdmin(), userDTO);
        return null;
    }
}
