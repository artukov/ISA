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
