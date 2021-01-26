package isa.project.pharmacyapp.dto;

import isa.project.pharmacyapp.model.DrugSpecification;

import java.math.BigInteger;
import java.util.List;

public class DrugSpecDTO {

    private BigInteger id;
    private BigInteger code;
    private String manufacturer;
    private String name;
    private String shape;
    private String type;
    private String composition;
    private String recom_consum;
    private String side_effects;

    public DrugSpecDTO() {
    }

    public DrugSpecDTO(BigInteger id, BigInteger code, String manufacturer, String name, String shape, String type, String composition, String recom_consum, String side_effects) {
        this.id = id;
        this.code = code;
        this.manufacturer = manufacturer;
        this.name = name;
        this.shape = shape;
        this.type = type;
        this.composition = composition;
        this.recom_consum = recom_consum;
        this.side_effects = side_effects;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getCode() {
        return code;
    }

    public void setCode(BigInteger code) {
        this.code = code;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getRecom_consum() {
        return recom_consum;
    }

    public void setRecom_consum(String recom_consum) {
        this.recom_consum = recom_consum;
    }

    public String getSide_effects() {
        return side_effects;
    }

    public void setSide_effects(String side_effects) {
        this.side_effects = side_effects;
    }
}
