package isa.project.pharmacyapp.dto;

import java.math.BigInteger;

public class DrugsFromReceiptPriceinPharmacyDTO {

    private Long id;
    private String name;
    private Double totalPrice;


    public DrugsFromReceiptPriceinPharmacyDTO() {
    }

    public DrugsFromReceiptPriceinPharmacyDTO(Long id, String name, Double totalPrice) {
        this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
