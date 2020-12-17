package isa.project.pharmacyapp.user_factory;

import isa.project.pharmacyapp.model.*;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User getUser(UserRoles role){
        if(role == null){
            return null;
        }
        if(role.equals(UserRoles.DERMATOLOGIST)){
            return new Dermatologist();
        }

        if(role.equals(UserRoles.PHARMACIST)){
            return new Pharmacist();
        }

        if(role.equals(UserRoles.PHARMACY_ADMIN)){
            return new PharmacyAdmin();
        }

        if(role.equals(UserRoles.PATIENT)){
            return new Patient();
        }

        if(role.equals(UserRoles.SUPPLIER)){
           return new Supplier();
        }
        if(role.equals(UserRoles.SYSTEM_ADMIN)){
            return new SystemAdmin();
        }

        return null;
    }


}
