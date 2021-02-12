package isa.project.pharmacyapp.registration_event;

import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.model.VerificationToken;
import isa.project.pharmacyapp.repository.UserRepository;
import isa.project.pharmacyapp.repository.VerificationTokenRepository;
import isa.project.pharmacyapp.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken newToken = new VerificationToken();
        newToken.setToken(token);
        newToken.setUser(user);
        newToken.setExpiryDate(newToken.calculateExpiryDate(VerificationToken.EXPIRATION));

        tokenRepository.save(newToken);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void saveRegisteredUser(User user) {
        user.setAuthorities(authorityService.findByName("USER"));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
