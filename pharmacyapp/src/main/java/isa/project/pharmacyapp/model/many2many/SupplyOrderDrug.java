package isa.project.pharmacyapp.model.many2many;

import isa.project.pharmacyapp.model.embedded_ids.SupplyOrderDrugID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "supply_drug")
public class SupplyOrderDrug  {

    @EmbeddedId
    private SupplyOrderDrugID id;

    @Column(nullable = false)
    private Integer amount;

    public SupplyOrderDrug() {
    }

    public SupplyOrderDrugID getId() {
        return id;
    }

    public void setId(SupplyOrderDrugID id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
