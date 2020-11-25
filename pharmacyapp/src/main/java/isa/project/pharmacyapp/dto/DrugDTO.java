package isa.project.pharmacyapp.dto;


import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.DrugSpecification;

import java.util.List;

public class DrugDTO {

    private Long id;
    private String name;
    private Long code;
    private String type;
    private String shape;
    private String manufacturer;
    private Boolean receipt = null;
    private DrugSpecification drugSpecification;
    private List<Long> substituteDrugs;

    public DrugDTO() {
    }

    public DrugDTO(Long id, String name, Long code, String type,
                   String shape, String manufacturer, Boolean receipt, DrugSpecification drugSpecification) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.type = type;
        this.shape = shape;
        this.manufacturer = manufacturer;
        this.receipt = receipt;
        this.drugSpecification = drugSpecification;
    }

    public static DrugDTO drug2DTO(Drug drug){
        DrugDTO retVal = new DrugDTO(
                drug.getId(),
                drug.getName(),
                drug.getCode(),
                drug.getType(),
                drug.getShape(),
                drug.getManufacturer(),
                drug.getReceipt(),
                drug.getDrugSpecification()
        );

        for(Drug iter : drug.getSubstituteDrugs()){
            retVal.getSubstituteDrugs().add(iter.getId());
        }

        return retVal;
    }

    public static void dto2Drug(Drug drug, DrugDTO dto){
        drug.setId(dto.getId());
        drug.setName(dto.getName());
        drug.setCode(dto.getCode());
        drug.setDrugSpecification(dto.getDrugSpecification());
        drug.setType(dto.getType());
        drug.setShape(dto.getShape());
        drug.setReceipt(dto.getReceipt());
        drug.setManufacturer(dto.getManufacturer());

    }

    public List<Long> getSubstituteDrugs() {
        return substituteDrugs;
    }

    public void setSubstituteDrugs(List<Long> substituteDrugs) {
        this.substituteDrugs = substituteDrugs;
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

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Boolean getReceipt() {
        return receipt;
    }

    public void setReceipt(Boolean receipt) {
        this.receipt = receipt;
    }

    public DrugSpecification getDrugSpecification() {
        return drugSpecification;
    }

    public void setDrugSpecification(DrugSpecification drugSpecification) {
        this.drugSpecification = drugSpecification;
    }
}
