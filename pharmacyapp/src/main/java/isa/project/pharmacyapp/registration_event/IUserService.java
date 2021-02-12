package isa.project.pharmacyapp.registration_event;

import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.model.VerificationToken;

public interface IUserService {
     void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String token);

    void saveRegisteredUser(User user);

    void deleteUser(User user);
}
