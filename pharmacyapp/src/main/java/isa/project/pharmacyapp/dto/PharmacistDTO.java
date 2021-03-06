package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import isa.project.pharmacyapp.model.Appointment;
import isa.project.pharmacyapp.model.Pharmacist;
import isa.project.pharmacyapp.model.UserRoles;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PharmacistDTO extends UserDTO{

    private List<Double> ratings;
    private List<Long> consultationId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date start_hour;
    private Integer hours;

    private Long pharmacyID;


    public PharmacistDTO() {
        super();
    }

    public PharmacistDTO(List<Double> ratings, List<Long> consultationId) {
        this.ratings = ratings;
        this.consultationId = consultationId;
    }

    public PharmacistDTO(Long id, String email, String password, String firstname, String lastname,
                         Long address_id, String phoneNumber, UserRoles role, Timestamp lastPasswordResetDate) {
        super(id, email, password, firstname, lastname, address_id, phoneNumber, role, lastPasswordResetDate);
    }

    public PharmacistDTO(Long id, String email, String password, String firstname, String lastname,
                         Long address_id, String phoneNumber, UserRoles role, Timestamp lastPasswordResetDate,
                         List<Double> ratings, List<Long> consultationId, Date start_hour, Integer hours, Long pharmacyID) {
        super(id, email, password, firstname, lastname, address_id, phoneNumber, role, lastPasswordResetDate);
        this.ratings = ratings;
        this.consultationId = consultationId;
        this.start_hour = start_hour;
        this.hours = hours;
        this.pharmacyID = pharmacyID;
    }

//    public PharmacistDTO(Long id, String email, String password, String firstname, String lastname, Long address_id,
//                         String phoneNumber, UserRoles role, List<Double> ratings, List<Long> consultationId,
//                         Date start_hour, Integer hours) {
//        super(id, email, password, firstname, lastname, address_id, phoneNumber, role);
//        this.ratings = ratings;
//        this.consultationId = consultationId;
//        this.start_hour = start_hour;
//        this.hours = hours;
//    }

    public static PharmacistDTO pharmacist2Dto(Pharmacist pharmacist) {
        PharmacistDTO retDto = new PharmacistDTO(
                pharmacist.getId(),
                pharmacist.getEmail(),
                pharmacist.getPassword(),
                pharmacist.getFirstname(),
                pharmacist.getLastname(),
                pharmacist.getAddress().getId(),
                pharmacist.getPhoneNumber(),
                UserRoles.PHARMACIST,
                pharmacist.getLastPasswordResetDate(),
                pharmacist.getRatings(),
                new ArrayList<>(),
                pharmacist.getStart_hour(),
                pharmacist.getHours(),
                pharmacist.getPharmacy().getId()
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

    public Date getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(Date start_hour) {
        this.start_hour = start_hour;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Long getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(Long pharmacyID) {
        this.pharmacyID = pharmacyID;
    }
}
