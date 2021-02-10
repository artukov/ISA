package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.EReceiptDTO;

public interface ERecepitService {
    public abstract EReceiptDTO findByPatient(Long patientId);
}
