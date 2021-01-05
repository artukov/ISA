package isa.project.pharmacyapp.model.embedded_ids;

import isa.project.pharmacyapp.model.Dermatologist;
import isa.project.pharmacyapp.model.Pharmacy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DermaPharmacyId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "derma_id")
    private Dermatologist dermatologist;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    /**
     * Object::equals() and Object::hashCode() have been overriden
     * because of the warning that showed up in the console during build.
     * They have no great meaning for the production of the code
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DermaPharmacyId)) return false;
        DermaPharmacyId that = (DermaPharmacyId) o;
        return Objects.equals(dermatologist, that.dermatologist) &&
                Objects.equals(pharmacy, that.pharmacy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dermatologist, pharmacy);
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
