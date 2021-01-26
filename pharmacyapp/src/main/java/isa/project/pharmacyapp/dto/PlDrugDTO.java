package isa.project.pharmacyapp.dto;

import isa.project.pharmacyapp.model.many2many.PriceListDrug;

public class PlDrugDTO {

    private Long priceListID;
    private Long drugID;
    private String name;
    private Double price;

    public PlDrugDTO() {
    }

    public PlDrugDTO(Long priceListID, Long drugID, String name, Double price) {
        this.priceListID = priceListID;
        this.drugID = drugID;
        this.name = name;
        this.price = price;
    }

    public static PlDrugDTO plDrug2DTO(PriceListDrug drug) {
        PlDrugDTO dto = new PlDrugDTO(
                drug.getId().getPriceList().getId(),
                drug.getId().getDrug().getId(),
                drug.getId().getDrug().getName(),
                drug.getPrice()
        );

        return dto;
    }

    public Long getPriceListID() {
        return priceListID;
    }

    public void setPriceListID(Long priceListID) {
        this.priceListID = priceListID;
    }

    public Long getDrugID() {
        return drugID;
    }

    public void setDrugID(Long drugID) {
        this.drugID = drugID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
