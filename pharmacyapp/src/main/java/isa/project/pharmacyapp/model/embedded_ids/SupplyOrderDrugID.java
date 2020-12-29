package isa.project.pharmacyapp.model.embedded_ids;

import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.SupplyOrder;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class SupplyOrderDrugID  implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "supply_id")
    private SupplyOrder supplyOrder;


    public SupplyOrderDrugID() {
    }


    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public SupplyOrder getSupplyOrder() {
        return supplyOrder;
    }

    public void setSupplyOrder(SupplyOrder supplyOrder) {
        this.supplyOrder = supplyOrder;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
