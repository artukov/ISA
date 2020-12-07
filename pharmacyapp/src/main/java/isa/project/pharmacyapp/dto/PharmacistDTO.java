package isa.project.pharmacyapp.dto;

import isa.project.pharmacyapp.model.Appointment;
import isa.project.pharmacyapp.model.Pharmacist;

import java.util.ArrayList;
import java.util.List;

public class PharmacistDTO extends UserDTO{

    private List<Double> ratings;
    private List<Long> consultationId;

    public PharmacistDTO() {
        super();
    }

    public PharmacistDTO(List<Double> ratings, List<Long> consultationId) {
        this.ratings = ratings;
        this.consultationId = consultationId;
    }

    public PharmacistDTO(Long id, String email, String password, String firstname, String lastname, Long address_id, String phoneNumber,
                         List<Double> ratings, List<Long> consultationId) {
        super(id, email, password, firstname, lastname, address_id, phoneNumber);
        this.ratings = ratings;
        this.consultationId = consultationId;
    }

    public static PharmacistDTO pharmacist2Dto(Pharmacist pharmacist) {
        PharmacistDTO retDto = new PharmacistDTO(
                pharmacist.getId(),
                pharmacist.getEmail(),
                pharmacist.getPassword(),
                pharmacist.getFirstname(),
                pharmacist.getLastname(),
                pharmacist.getAddress().getId(),
                pharmacist.getPhoneNumber(),
                pharmacist.getRatings(),
                new ArrayList<>()
        );
        for(Appointment appointment : pharmacist.getAppointment()){
            retDto.consultationId.add(appointment.getId());
        }

        return retDto;

    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(List<Double> ratings) {
        this.ratings = ratings;
    }

    public List<Long> getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(List<Long> consultationId) {
        this.consultationId = consultationId;
    }
}
