package isa.project.pharmacyapp.model.embedded_ids;

import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.Pharmacy;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class PharmacyDrugID implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "drug_id")
    private Drug drug;

    public PharmacyDrugID() {
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
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
