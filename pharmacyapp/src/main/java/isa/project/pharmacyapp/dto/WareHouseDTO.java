package isa.project.pharmacyapp.dto;

import isa.project.pharmacyapp.model.WareHouse;
import isa.project.pharmacyapp.model.many2many.WareHouseDrug;

import java.util.ArrayList;
import java.util.List;

public class WareHouseDTO {

    private Long id;
    private Long supplierID;
    private Integer size;
    private List<Long> drugsID;
    private List<String> drugsNames;
    private List<Integer> amount;


    public WareHouseDTO() {
    }

    public WareHouseDTO(Long id, Long supplierID, Integer size,
                        List<Long> drugsID, List<String> drugsNames, List<Integer> amount) {
        this.id = id;
        this.supplierID = supplierID;
        this.size = size;
        this.drugsID = drugsID;
        this.drugsNames = drugsNames;
        this.amount = amount;
    }

    public static WareHouseDTO warehouse2dto(WareHouse wareHouse) {
        WareHouseDTO dto = new WareHouseDTO(
                wareHouse.getId(),
                wareHouse.getSupplier().getId(),
                wareHouse.getSize(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        for(WareHouseDrug drug : wareHouse.getDrugs()){
            dto.getDrugsID().add(drug.getId().getDrug().getId());
            dto.getDrugsNames().add(drug.getId().getDrug().getName());
            dto.getAmount().add(drug.getAmount());
        }
        return dto;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Long supplierID) {
        this.supplierID = supplierID;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Long> getDrugsID() {
        return drugsID;
    }

    public void setDrugsID(List<Long> drugsID) {
        this.drugsID = drugsID;
    }

    public List<String> getDrugsNames() {
        return drugsNames;
    }

    public void setDrugsNames(List<String> drugsNames) {
        this.drugsNames = drugsNames;
    }

    public List<Integer> getAmount() {
        return amount;
    }

    public void setAmount(List<Integer> amount) {
        this.amount = amount;
    }
}
