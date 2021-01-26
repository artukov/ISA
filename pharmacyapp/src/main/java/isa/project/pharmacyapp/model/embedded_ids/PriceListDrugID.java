package isa.project.pharmacyapp.model.embedded_ids;

import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.PriceList;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PriceListDrugID implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pricelist_id", referencedColumnName = "id")
    private PriceList priceList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "drug_id", referencedColumnName = "id")
    private Drug drug;

    public PriceListDrugID() {
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
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
        if (!(o instanceof PriceListDrugID)) return false;
        PriceListDrugID that = (PriceListDrugID) o;
        return priceList.equals(that.priceList) && drug.equals(that.drug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priceList, drug);
    }
}
