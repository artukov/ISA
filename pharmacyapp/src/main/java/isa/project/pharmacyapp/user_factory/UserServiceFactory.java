package isa.project.pharmacyapp.user_factory;

import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.service.DermatologistService;
import isa.project.pharmacyapp.service.PharmacistService;
import isa.project.pharmacyapp.service.PharmacyAdminService;
import isa.project.pharmacyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Factory Pattern for user services
 * */
@Component
public class UserServiceFactory {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;
    @Autowired
    private PharmacistService pharmacistService;
    @Autowired
    private DermatologistService dermatologistService;
    @Autowired
    private PharmacyAdminService pharmacyAdminService;


    public UserService getUserService(UserRoles role){
        if(role == null){
            return userService;
        }
        if(role.equals(UserRoles.DERMATOLOGIST)){
            return dermatologistService;
        }

        if(role.equals(UserRoles.PHARMACIST)){
            return pharmacistService;
        }

        if(role.equals(UserRoles.PHARMACY_ADMIN)){
            return pharmacyAdminService;
        }

        if(role.equals(UserRoles.PATIENT)){
            /**
             * TODO
             * PatientService
             * */
        }

        if(role.equals(UserRoles.SUPPLIER)){
            /**
             * TODO
             * SupplierService
             * */
        }
        if(role.equals(UserRoles.SYSTEM_ADMIN)){
            /**
             * TODO
             * SystemAdminService*/
        }

        return userService;
    }
}
