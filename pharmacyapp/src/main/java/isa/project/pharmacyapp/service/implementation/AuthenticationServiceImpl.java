package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.model.Authority;
import isa.project.pharmacyapp.repository.AuthenticationRepository;
import isa.project.pharmacyapp.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthorityService {

    @Autowired
    private AuthenticationRepository repository;

    @Override
    public List<Authority> findById(Long id) {
        Authority authority = repository.getOne(id);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);
        return authorities;
    }

    @Override
    public List<Authority> findByName(String name) {
        Authority authority = repository.findByName(name);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);
        return authorities;
    }
}
