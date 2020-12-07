package isa.project.pharmacyapp.dto;

import isa.project.pharmacyapp.model.Appointment;
import isa.project.pharmacyapp.model.Dermatologist;
import isa.project.pharmacyapp.model.Examination;

import java.util.ArrayList;
import java.util.List;

public class DermatologistDTO extends UserDTO {


    private List<Long> examinationsId;


    public DermatologistDTO() {
    }

    public DermatologistDTO(Long id, String email, String password, String firstname, String lastname,
                            Long address_id, String phoneNumber, List<Long> examinationsId) {
        super(id, email, password, firstname, lastname, address_id, phoneNumber);
        this.examinationsId = examinationsId;
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
                new ArrayList<>()
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
}
