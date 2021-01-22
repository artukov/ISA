package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.databind.node.BigIntegerNode;
import isa.project.pharmacyapp.model.Drug;

import java.math.BigInteger;

public class PharmaDrugDTO {

    private String name;
    private String type;
    private BigIntegerNode pharmacy_id;
    private Double price;


    public PharmaDrugDTO() {
    }

    public PharmaDrugDTO(String name, String type, BigIntegerNode pharmacy_id, Double price) {
        this.name = name;
        this.type = type;
        this.pharmacy_id = pharmacy_id;
        this.price = price;
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

    public BigIntegerNode getPharmacy_id() {
        return pharmacy_id;
    }

    public void setPharmacy_id(BigIntegerNode pharmacy_id) {
        this.pharmacy_id = pharmacy_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
