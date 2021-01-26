package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.AbsenceRequestDTO;
import isa.project.pharmacyapp.dto.ReservationDTO;
import isa.project.pharmacyapp.model.AbsenceRequest;
import isa.project.pharmacyapp.model.Reservation;

public interface AbsenceRequestService {
    public void createNewAbsenceRequest(AbsenceRequestDTO absenceRequestDTO) throws Exception;

    void saveAbsenceRequest(AbsenceRequest absenceRequest, AbsenceRequestDTO absenceRequestDTO) throws Exception;

}
