package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import isa.project.pharmacyapp.model.Appointment;
import isa.project.pharmacyapp.model.Dermatologist;
import isa.project.pharmacyapp.model.Examination;
import isa.project.pharmacyapp.model.UserRoles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DermatologistDTO extends UserDTO {


    private List<Long> examinationsId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date start_hour;
    private Integer hours;

    private Double ratings;


    public DermatologistDTO() {
    }

    public DermatologistDTO(Long id, String email, String password, String firstname, String lastname,
                            Long address_id, String phoneNumber, UserRoles role,
                            List<Long> examinationsId, Date start_hour, Integer hours) {
        super(id, email, password, firstname, lastname, address_id, phoneNumber, role);
        this.examinationsId = examinationsId;
        this.start_hour = start_hour;
        this.hours = hours;
    }


    public static DermatologistDTO dermatologist2Dto(Dermatologist dermatologist){
        DermatologistDTO retDto = new DermatologistDTO(
                dermatologist.getId(),
                dermatologist.getEmail(),
                dermatologist.getPassword(),
                dermatologist.getFirstname(),
                dermatologist.getLastname(),
                dermatologist.getAddress().getId(),
                dermatologist.getPhoneNumber(),
                UserRoles.DERMATOLOGIST,
                new ArrayList<>(),
                null,
                null
        );
        for(Appointment appointment : dermatologist.getAppointment()){
            retDto.examinationsId.add(appointment.getId());
        }
        return retDto;


    }


    public List<Long> getExaminationsId() {
        return examinationsId;
    }

    public void setExaminationsId(List<Long> examinationsId) {
        this.examinationsId = examinationsId;
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

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }
}
