package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.ReservationDTO;
import isa.project.pharmacyapp.model.Reservation;

import java.util.List;

public interface ReservationService {

    void createNewReservation(ReservationDTO reservationDTO) throws Exception;
    void modifyReservation(Long id, ReservationDTO reservationDTO);

    void saveReservation(Reservation reservation, ReservationDTO reservationDTO) throws Exception;

    void drugAccepted(Long id) throws Exception;
    void dispenseDrug(Long id) throws Exception;
    ReservationDTO getByIdAndPharmacy(Long reservationId, Long pharmacyId);
}
