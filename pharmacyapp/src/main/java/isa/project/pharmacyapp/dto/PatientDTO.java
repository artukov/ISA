package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import isa.project.pharmacyapp.model.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientDTO extends UserDTO {

    private Integer points = 0;
    private String category;
    private Integer penalties = 0;
    public Address address;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    public Date appointmentDate;
    public List<Long> reservation;
    public List<Long> appointment;
    public List<Long> allergy;
    public List<Long> promotions;
    public List<Long> complaints;
    public List<Long> eReceipt;

    public PatientDTO() {
    }

//    public PatientDTO(Long id, String email, String password, String firstname, String lastname, Long address_id, String phoneNumber, Integer points, String category, Integer penalties, Address address, List<Long> reservation, List<Long> appointment, List<Long> allergy, List<Long> promotions, List<Long> complaints, List<Long> eReceipt ) {
//        super(id, email, password, firstname, lastname, address_id, phoneNumber);
//        this.points = points;
//        this.category = category;
//        this.penalties = penalties;
//        this.address = address;
//        this.reservation = reservation;
//        this.appointment = appointment;
//        this.allergy = allergy;
//        this.promotions = promotions;
//        this.complaints = complaints;
//        this.eReceipt = eReceipt;
//    }


//    public PatientDTO(Long id, String email, String password, String firstname, String lastname,
//                      Long address_id, String phoneNumber, UserRoles role) {
//        super(id, email, password, firstname, lastname, address_id, phoneNumber, role);
//    }


    public PatientDTO(Long id, String email, String password, String firstname,
                      String lastname, Long address_id, String phoneNumber, UserRoles role,
                      Timestamp lastPasswordResetDate) {
        super(id, email, password, firstname, lastname, address_id, phoneNumber, role, lastPasswordResetDate);
    }

    public PatientDTO(Long id, String email, String password, String firstname, String lastname, Long address_id, String phoneNumber, UserRoles role, Timestamp lastPasswordResetDate, Integer points, String category, Integer penalties, Address address, Date appointmentDate, List<Long> reservation, List<Long> appointment, List<Long> allergy, List<Long> promotions, List<Long> complaints, List<Long> eReceipt) {
        super(id, email, password, firstname, lastname, address_id, phoneNumber, role, lastPasswordResetDate);
        this.points = points;
        this.category = category;
        this.penalties = penalties;
        this.address = address;
        this.appointmentDate = appointmentDate;
        this.reservation = reservation;
        this.appointment = appointment;
        this.allergy = allergy;
        this.promotions = promotions;
        this.complaints = complaints;
        this.eReceipt = eReceipt;
    }

    //
//    public PatientDTO(Long id, String email, String password, String firstname,
//                      String lastname, Long address_id, String phoneNumber, UserRoles role, Integer points, String category,
//                      Integer penalties, Address address,
//                      List<Long> reservation, List<Long> appointment, List<Long> allergy, List<Long> promotions,
//                      List<Long> complaints, List<Long> eReceipt) {
//        super(id, email, password, firstname, lastname, address_id, phoneNumber, role);
//        this.points = points;
//        this.category = category;
//        this.penalties = penalties;
//        this.address = address;
//        this.reservation = reservation;
//        this.appointment = appointment;
//        this.allergy = allergy;
//        this.promotions = promotions;
//        this.complaints = complaints;
//        this.eReceipt = eReceipt;
//    }

    public static PatientDTO patient2Dto(Patient patient){
        PatientDTO retDto = new PatientDTO(
                patient.getId(),
                patient.getEmail(),
                patient.getPassword(),
                patient.getFirstname(),
                patient.getLastname(),
                patient.getAddress().getId(),
                patient.getPhoneNumber(),
                patient.getRole(),
                patient.getLastPasswordResetDate(),
                patient.getPoints(),
                patient.getCategory(),
                patient.getPenalties(),
                patient.getAddress(),
                null,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        for(Reservation reservation : patient.getReservation()){
            retDto.reservation.add(reservation.getId());
        }
        for(Appointment appointment : patient.getAppointment()){
            retDto.appointment.add(appointment.getId());
        }
        for(Allergy allergy : patient.getAllergy()){
            retDto.allergy.add(allergy.getId());
        }
        for(Promotions promotions : patient.getPromotions()){
            retDto.promotions.add(promotions.getId());
        }
        for(Complaints complaints : patient.getComplaints()){
            retDto.complaints.add(complaints.getId());
        }
        for(EReceipt eReceipt : patient.getEReceipt()){
            retDto.eReceipt.add(eReceipt.getId());
        }

        return retDto;
    }

    public static void dto2Patient(Patient patient, PatientDTO dto){
        UserDTO.dto2User(patient,dto);
        patient.setPoints(dto.getPoints());
        patient.setCategory(dto.getCategory());
        patient.setPenalties(dto.getPenalties());
//        patient.setComplaints(dto.getComplaints());
        patient.setAddress(dto.getAddress());
//        patient.setReservation(dto.getReservation());
//        patient.setAppointment(dto.getAppointment());
//        patient.setAllergy(dto.getAllergy());
//        patient.setPromotions(dto.getPromotions());
//        patient.setComplaints(dto.getComplaints());
//        patient.setEReceipt(dto.geteReceipt());

    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPenalties() {
        return penalties;
    }

    public void setPenalties(Integer penalties) {
        this.penalties = penalties;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Long> getReservation() {
        return reservation;
    }

    public void setReservation(List<Long> reservation) {
        this.reservation = reservation;
    }

    public List<Long> getAppointment() {
        return appointment;
    }

    public void setAppointment(List<Long> appointment) {
        this.appointment = appointment;
    }

    public List<Long> getAllergy() {
        return allergy;
    }

    public void setAllergy(List<Long> allergy) {
        this.allergy = allergy;
    }

    public List<Long> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Long> promotions) {
        this.promotions = promotions;
    }

    public List<Long> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Long> complaints) {
        this.complaints = complaints;
    }

    public List<Long> geteReceipt() {
        return eReceipt;
    }

    public void seteReceipt(List<Long> eReceipt) {
        this.eReceipt = eReceipt;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
