package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.EReceiptDTO;

import java.util.List;

public interface ERecepitService {
    public abstract List<EReceiptDTO> findByPatient(Long patientId);
}
