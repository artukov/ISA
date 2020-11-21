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
     * Static method that return DTO object of the Object
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

    public static void dto2pharmacyAdmin(PharmacyAdmin admin, PharmacyAdminDTO dto){
        //admin.setId(dto.getId());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        admin.setFirstname(dto.getFirstname());
        admin.setLastname(dto.getLastname());
        admin.setPhoneNumber(dto.getPhoneNumber());

    }
}
