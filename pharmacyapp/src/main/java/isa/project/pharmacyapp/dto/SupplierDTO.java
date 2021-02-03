package isa.project.pharmacyapp.dto;

import isa.project.pharmacyapp.model.Address;
import isa.project.pharmacyapp.model.Supplier;
import isa.project.pharmacyapp.model.UserRoles;
import isa.project.pharmacyapp.model.many2many.SupplierOrder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SupplierDTO extends UserDTO{


    private Address address;
    private List<OrderSupplierDTO> orders;

    public SupplierDTO() {
    }

    public SupplierDTO(Long id, String email, String password, String firstname, String lastname,
                       Long address_id, String phoneNumber, UserRoles role, Timestamp lastPasswordResetDate, Address address) {
        super(id, email, password, firstname, lastname, address_id, phoneNumber, role, lastPasswordResetDate);
        this.address = address;
    }

    public SupplierDTO(Long id, String email, String password, String firstname, String lastname, Long address_id,
                       String phoneNumber, UserRoles role, Timestamp lastPasswordResetDate,
                       Address address, List<OrderSupplierDTO> orders) {
        super(id, email, password, firstname, lastname, address_id, phoneNumber, role, lastPasswordResetDate);
        this.address = address;
        this.orders = orders;
    }

//    public SupplierDTO(Long id, String email, String password, String firstname,
//                       String lastname, Long address_id, String phoneNumber, UserRoles role,
//                       Address address, List<OrderSupplierDTO> orders) {
//        super(id, email, password, firstname, lastname, address_id, phoneNumber, role);
//        this.address = address;
//        this.orders = orders;
//    }

    public static SupplierDTO supplier2DTO(Supplier supplier) {
        SupplierDTO dto = new SupplierDTO(
                supplier.getId(),
                supplier.getEmail(),
                supplier.getPassword(),
                supplier.getFirstname(),
                supplier.getLastname(),
                supplier.getAddress().getId(),
                supplier.getPhoneNumber(),
                supplier.getRole(),
                supplier.getLastPasswordResetDate(),
                supplier.getAddress(),
                new ArrayList<>()
        );
        for(SupplierOrder order : supplier.getOrders()){
            dto.getOrders().add(OrderSupplierDTO.supplierOrder2DTO(order));
        }

        return dto;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderSupplierDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderSupplierDTO> orders) {
        this.orders = orders;
    }
}
