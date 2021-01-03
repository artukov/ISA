package isa.project.pharmacyapp.model.embedded_ids;

import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.WareHouse;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WareHouseDrugID implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse_id")
    private WareHouse wareHouse;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "drug_id")
    private Drug drug;

    public WareHouseDrugID() {
    }

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WareHouseDrugID)) return false;
        WareHouseDrugID that = (WareHouseDrugID) o;
        return wareHouse.equals(that.wareHouse) &&
                drug.equals(that.drug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wareHouse, drug);
    }
}
