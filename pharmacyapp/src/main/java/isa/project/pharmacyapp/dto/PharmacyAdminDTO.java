package isa.project.pharmacyapp.dto;

import isa.project.pharmacyapp.model.Address;
import isa.project.pharmacyapp.model.PharmacyAdmin;

/**
 * DTO pattern implementation
 * PharmacyAdmin class
 * */
public class PharmacyAdminDTO extends UserDTO {

    private Long pharmacy_id;

    public PharmacyAdminDTO() {
        super();
    }

    public PharmacyAdminDTO(Long id, String email, String password, String firstname, String lastname, Long address_id, String phoneNumber, Long pharmacy_id) {
        super(id, email, password, firstname, lastname, address_id, phoneNumber);
        this.pharmacy_id = pharmacy_id;
    }



    public Long getPharmacy_id() {
        return pharmacy_id;
    }

    public void setPharmacy_id(Long pharmacy_id) {
        this.pharmacy_id = pharmacy_id;
    }

    /**
     * Static method that returns DTO object of the Object
     * @param admin
     * */
    public static PharmacyAdminDTO pharmacyAdmin2DTO(PharmacyAdmin admin){
        PharmacyAdminDTO adminDTO = new PharmacyAdminDTO(
                admin.getId(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getFirstname(),
                admin.getLastname(),
                admin.getAddress().getId(),
                admin.getPhoneNumber(),
                admin.getPharmacy().getId()
        );
        return adminDTO;
    }

    public static PharmacyAdminDTO createPharmacyDTO(UserDTO userDTO) {
        return new PharmacyAdminDTO(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getFirstname(),
                userDTO.getLastname(),
                userDTO.address_id,
                userDTO.getPhoneNumber(),
                1L
        );
    }


}
