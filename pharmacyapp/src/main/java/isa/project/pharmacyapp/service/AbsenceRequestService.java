package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.AbsenceRequestDTO;
import isa.project.pharmacyapp.dto.ReservationDTO;
import isa.project.pharmacyapp.model.AbsenceRequest;
import isa.project.pharmacyapp.model.Reservation;

import java.util.List;

public interface AbsenceRequestService {
    public abstract void createNewAbsenceRequest(AbsenceRequestDTO absenceRequestDTO) throws Exception;

    public abstract void saveAbsenceRequest(AbsenceRequest absenceRequest, AbsenceRequestDTO absenceRequestDTO) throws Exception;


    List<AbsenceRequestDTO> findAllPharmacyPharmacistUnanswered(Long pharmacyID);

    void modifyWithStatus(Long id, AbsenceRequestDTO requestDTO) throws Exception;

    List<AbsenceRequestDTO> findAllDermatologistsAbsenceRequests();
}
