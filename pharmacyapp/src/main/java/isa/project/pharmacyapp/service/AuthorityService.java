package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.model.Authority;

import java.util.List;

public interface AuthorityService {

    public abstract List<Authority> findById(Long id);
    public abstract List<Authority> findByName(String name);
}
