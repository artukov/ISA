package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.databind.node.BigIntegerNode;
import isa.project.pharmacyapp.model.Appointment;
import isa.project.pharmacyapp.model.Dermatologist;
import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.UserRoles;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PharmaDrugDTO {

    private String name;
    private String type;
    private BigInteger pharmacy_id;
    private Double price;


    public PharmaDrugDTO() {
    }

    public PharmaDrugDTO(String name, String type, BigInteger pharmacy_id, Double price) {
        this.name = name;
        this.type = type;
        this.pharmacy_id = pharmacy_id;
        this.price = price;
    }

    public static PharmaDrugDTO pharmaDrug2Dto(String name, String type, BigInteger pharmacy_id, Double price){
        PharmaDrugDTO retDto = new PharmaDrugDTO(
          name,
          type,
          pharmacy_id,
          price
        );
        return retDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigInteger getPharmacy_id() {
        return pharmacy_id;
    }

    public void setPharmacy_id(BigInteger pharmacy_id) {
        this.pharmacy_id = pharmacy_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
