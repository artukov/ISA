package isa.project.pharmacyapp.model.many2many;

import isa.project.pharmacyapp.model.embedded_ids.PharmacyDrugID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pharmacy_drug")
public class PharmacyDrug {

    @EmbeddedId
    private PharmacyDrugID id;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price")
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PharmacyDrug() {
    }

    public PharmacyDrugID getId() {
        return id;
    }

    public void setId(PharmacyDrugID id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
