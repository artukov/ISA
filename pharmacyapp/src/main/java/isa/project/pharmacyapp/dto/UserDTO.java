package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.model.UserRoles;

public class UserDTO {

    protected Long id;
    protected String email;
    protected String password;
    protected String firstname;
    protected String lastname;
    protected Long address_id;
    protected String phoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    protected UserRoles role;

    public UserDTO() {
    }

//    public UserDTO(Long id, String email, String password, String firstname, String lastname,
//                   Long address_id, String phoneNumber) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.address_id = address_id;
//        this.phoneNumber = phoneNumber;
//    }

    public UserDTO(Long id, String email, String password, String firstname,
                   String lastname, Long address_id, String phoneNumber, UserRoles role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address_id = address_id;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public static UserDTO user2DTO(User user){
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstname(),
                user.getLastname(),
                user.getAddress().getId(),
                user.getPhoneNumber(),
                user.getRole()
        );

        return userDTO;

    }
    public static void dto2User(User user, UserDTO userDTO){

        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setPhoneNumber(userDTO.getPhoneNumber());


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
}
